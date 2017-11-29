package xiangqi.studenthphong.versions.betaxiangqi;

import java.util.*;
import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studenthphong.common.XiangqiPieceImpl;
import static xiangqi.studenthphong.testutil.TestCoordinate.makeCoordinate;

public class BetaXiangqiGame implements XiangqiGame {
	
	private int round;
	private boolean illegalCheck;
	private boolean gameEndCheck;
	private List<XiangqiPiece> boardPiece = new ArrayList<XiangqiPiece>();
	private List<Integer> boardLoc = new ArrayList<Integer>();

	/*
	 * Initialize board and variables
	 */
	public BetaXiangqiGame() {
		// Prepare board and fill location list
		// Add red pieces
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.RED));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.RED));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.RED));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.RED));
		
		// Add black pieces
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.SOLDIER, XiangqiColor.BLACK));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.GENERAL, XiangqiColor.BLACK));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.ADVISOR, XiangqiColor.BLACK));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));
		boardPiece.add(XiangqiPieceImpl.makePiece(XiangqiPieceType.CHARIOT, XiangqiColor.BLACK));
		
		// Add board location information (with red perspective)
		// If prompt is about black perspective, consider the array backward
		// Red location
		boardLoc.add(11);
		boardLoc.add(15);
		boardLoc.add(12);
		boardLoc.add(14);
		boardLoc.add(13);
		boardLoc.add(23);
		
		// Black location
		boardLoc.add(43);
		boardLoc.add(53);
		boardLoc.add(54);
		boardLoc.add(52);
		boardLoc.add(55);
		boardLoc.add(51);
		
		// Initialize value for game
		round = 0;
		gameEndCheck = false;
	}
	
	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		XiangqiCoordinate newSource, newDest;
		// Count turn and decide whose turn
		round++;
		newSource = turnDecider(source);
		newDest = turnDecider(destination);
		
		// Initialize variables
		XiangqiPiece p = findStartingPiece(newSource);
		
		// Attempt to move piece from source coordinate to destination coordinate
		performMovePiece(p, newSource, newDest);
		
		// If move is legal, check for result
		if (illegalCheck == false) {
			// Perform a check of whether GENERALS are facing each other
			boolean generalsFaceEachOther = checkGenerals();
			// Flag for end game is TRUE, return winner
			if (gameEndCheck == true) {
				if (generalsFaceEachOther == false) {
					if (round % 2 == 1) { // RED turn
						return MoveResult.RED_WINS;
					}
					else { // BLACK turn
						return MoveResult.BLACK_WINS;
					}
				} else {
					if (round % 2 == 1) { // RED turn
						return MoveResult.BLACK_WINS;
					}
					else { // BLACK turn
						return MoveResult.RED_WINS;
					}
				}
			}
			// Flag for end game is FALSE, proceed to check for move count
			else if (round >= 20) {
				return MoveResult.DRAW;
			} else return MoveResult.OK;
		}
		else { // Move is illegal, revert move count and return ILLEGAL
			round--;
			return MoveResult.ILLEGAL;
		}
	}

	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#getMoveMessage()
	 */
	@Override
	public String getMoveMessage() {
		if (illegalCheck == true) {
			return "Move is illegal";
		}
		else return "";
	}

	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#getPieceAt(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiColor)
	 */
	@Override
	public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
		// Initialize variables
		int exactLoc = 0;
		int i;
		XiangqiPiece p = XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
		
		// Check for perspective to adjust accordingly
		if (aspect == XiangqiColor.RED) {
			exactLoc = where.getRank() * 10 + where.getFile();
		} else if (aspect == XiangqiColor.BLACK) {
			exactLoc = (5 - where.getRank() + 1) * 10 + (5 - where.getFile() + 1);
		}
		
		// Go through the board to find the piece
		for (i = 0; i < boardLoc.size(); i++) {
			if (boardLoc.get(i) == exactLoc) {
				p = boardPiece.get(i);
				break;
			}
		}
		return p;
	}
	
	/**
	 * Function to maneuver coordinate to match with respective player's point of view
	 * @param coordinate that is perceived by player
	 * @return coordinate that is controlled by fixed, single perspective
	 */
	private XiangqiCoordinate turnDecider(XiangqiCoordinate coordinate) {
		XiangqiCoordinate result = coordinate;
		if (round % 2 == 0) { // BLACK turn
			result = makeCoordinate(5 - coordinate.getRank() + 1, 5 - coordinate.getFile() + 1);
		}
		return result;
	}
	
	/**
	 * Function to determine the type of the starting piece at source coordinate
	 * @param source coordinate of the starting piece
	 * @return type of the starting piece
	 */
	private XiangqiPiece findStartingPiece(XiangqiCoordinate source) {
		XiangqiPiece p = XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
		int i;
		int start = source.getRank() * 10 + source.getFile();
		illegalCheck = true;
		// Go through the board to determine the starting piece
				// If no piece in source location, consider move illegal
				for (i = 0; i < boardLoc.size(); i++) {
					if (boardLoc.get(i) == start) {
						// Check that the player use piece of correct color
						if (((round % 2 == 1) && (boardPiece.get(i).getColor() == XiangqiColor.RED))
						 || ((round % 2 == 0) && (boardPiece.get(i).getColor() == XiangqiColor.BLACK))) {
							illegalCheck = false;
							p = boardPiece.get(i);
							break;
						} else {
							illegalCheck = true;
							break;
						}
					}
				}
		return p;
	}
	
	/**
	 * Perform a move of a Xiangqi Piece from one location to another according to its piece type
	 * @param p Xiangqi piece at source coordinate
	 * @param source coordinate that the piece originates
	 * @param destination coordinate that the piece attempts to go to
	 */
	private void performMovePiece(XiangqiPiece p, XiangqiCoordinate source, XiangqiCoordinate destination) {
		// If everything is illegal so far, perform move piece
		if (illegalCheck == false) {
			switch (p.getPieceType()) {
			case CHARIOT : {
				chariotMove(source, destination);
				break;
			}
			case ADVISOR : {
				advisorMove(source, destination);
				break;
			}
			case SOLDIER : {
				soldierMove(source, destination);
				break;
			}
			case GENERAL : {
				generalMove(source, destination);
				break;
			}
			default:
				break;
			}
		}
	}
	
	/**
	 * Function to specifically move a CHARIOT piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void chariotMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, tempLoc, sourceIndex = 0;
		XiangqiPiece p = XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

		int start = source.getRank() * 10 + source.getFile();
		int end = destination.getRank() * 10 + destination.getFile();

		for (i = 0; i < boardLoc.size(); i++) {
			if (boardLoc.get(i) == start) {
				sourceIndex = i;
				p = boardPiece.get(i);
				break;
			}
		}

		// Check whether the end piece match the accepted movement of CHARIOT
		// If not, consider move illegal

		// Same rank scenario
		if (source.getRank() == destination.getRank()) {
			// Case of source file less than destination file (moving right)
			if (source.getFile() < destination.getFile()) {
				// Check for any obstacle along the way
				for (i = source.getFile(); i < destination.getFile(); i++) {
					tempLoc = source.getRank() * 10 + i;
					if ((boardLoc.contains(tempLoc)) && (i != source.getFile())) {
						illegalCheck = true;
						break;
					}
				}
			}

			// Case of source file more than destination file (moving left)
			else if (source.getFile() > destination.getFile()) {
				// Check for any obstacle along the way
				for (i = source.getFile(); i > destination.getFile(); i--) {
					tempLoc = source.getRank() * 10 + i;
					if ((boardLoc.contains(tempLoc)) && (i != source.getFile())) {
						illegalCheck = true;
						break;
					}
				}
			}
			// If not (source file = destination file), consider move illegal
			else illegalCheck = true;
		}

		// Same file scenario
		else if (source.getFile() == destination.getFile()) {
			// Case of source rank less than destination rank (moving up)
			if (source.getRank() < destination.getRank()) {
				// Check for any obstacle along the way
				for (i = source.getRank(); i < destination.getRank(); i++) {
					tempLoc = i * 10 + source.getFile();
					if ((boardLoc.contains(tempLoc)) && (i != source.getRank())) {
						illegalCheck = true;
						break;
					}
				}
			}

			// Case of source rank more than destination rank (moving down)
			else if (source.getRank() > destination.getRank()) {
				// Check for any obstacle along the way
				for (i = source.getRank(); i > destination.getRank(); i--) {
					tempLoc = i * 10 + source.getFile();
					if ((boardLoc.contains(tempLoc)) && (i != source.getRank())) {
						illegalCheck = true;
						break;
					}
				}
			}
		}

		// If not fit to any scenario, consider move illegal
		else illegalCheck = true;

		// Now check further for the destination location
		if (illegalCheck == false) {
			// Check whether there is a piece at destination location
			for (i = 0; i < boardLoc.size(); i++) {
				if (boardLoc.get(i) == end) {
					XiangqiPiece destPiece = boardPiece.get(i);

					// If destination piece is of other color, move is legal with destination piece consumed
					// Else move illegal (cannot consume piece of same color)
					if (destPiece.getColor() != p.getColor()) {
						// Trigger flag if piece consumed is GENERAL
						if (destPiece.getPieceType() == XiangqiPieceType.GENERAL) {
							gameEndCheck = true;
						}
						
						// Remove consumed piece from board, and update current piece location
						boardLoc.set(sourceIndex, end);
						boardLoc.remove(i);
						boardPiece.remove(i);
						break;
					} else {
						illegalCheck = true;
						break;
					}
				} 
				// Find no piece at all
				else if (i == boardLoc.size() - 1) boardLoc.set(sourceIndex, end);
			}
		}
	}
	
	/**
	 * Function to specifically move an ADVISOR piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void advisorMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0;
		XiangqiPiece p = XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

		int start = source.getRank() * 10 + source.getFile();
		int end = destination.getRank() * 10 + destination.getFile();

		for (i = 0; i < boardLoc.size(); i++) {
			if (boardLoc.get(i) == start) {
				sourceIndex = i;
				p = boardPiece.get(i);
				break;
			}
		}
		
		// Check whether the end piece match the accepted movement of ADVISOR
		// If not, consider move illegal
		
		if ((destination.getRank() == source.getRank() + 1) && (destination.getFile() == source.getFile() - 1)    // Diag. UP & LEFT 
		 || (destination.getRank() == source.getRank() + 1) && (destination.getFile() == source.getFile() + 1)    // Diag. UP & RIGHT
		 || (destination.getRank() == source.getRank() - 1) && (destination.getFile() == source.getFile() - 1)    // Diag. DOWN & LEFT
		 || (destination.getRank() == source.getRank() - 1) && (destination.getFile() == source.getFile() + 1)) { // Diag. DOWN & RIGHT
			for (i = 0; i < boardLoc.size(); i++) {
				if (boardLoc.get(i) == end) {
					XiangqiPiece destPiece = boardPiece.get(i);
					// If destination piece is of other color, move is legal with destination piece consumed
					// Else move illegal (cannot consume piece of same color)
					if (destPiece.getColor() != p.getColor()) {
						// Trigger flag if piece consumed is GENERAL
						if (destPiece.getPieceType() == XiangqiPieceType.GENERAL) {
							gameEndCheck = true;
						}
						
						// Remove consumed piece from board, and update current piece location
						boardLoc.set(sourceIndex, end);
						boardLoc.remove(i);
						boardPiece.remove(i);
						break;
					} else {
						illegalCheck = true;
						break;
					}
				} 
				// Find no piece at all
				else if (i == boardLoc.size() - 1) boardLoc.set(sourceIndex, end);
			}
		}
		// If not fit to any scenario, consider move illegal
		else illegalCheck = true;
	}
	
	/**
	 * Function to specifically move a SOLDIER piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void soldierMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0;
		XiangqiPiece p = XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

		int start = source.getRank() * 10 + source.getFile();
		int end = destination.getRank() * 10 + destination.getFile();

		for (i = 0; i < boardLoc.size(); i++) {
			if (boardLoc.get(i) == start) {
				sourceIndex = i;
				p = boardPiece.get(i);
				break;
			}
		}

		// Check whether the end piece match the accepted movement of SOLDIER
		// If not, consider move illegal
		if (((destination.getRank() == source.getRank() + 1) && (destination.getFile() == source.getFile()) && (p.getColor() == XiangqiColor.RED))
		 || ((destination.getRank() == source.getRank() - 1) && (destination.getFile() == source.getFile()) && (p.getColor() == XiangqiColor.BLACK))){
			for (i = 0; i < boardLoc.size(); i++) {
				if (boardLoc.get(i) == end) {
					XiangqiPiece destPiece = boardPiece.get(i);
					// If destination piece is of other color, move is legal with destination piece consumed
					// Else move illegal (cannot consume piece of same color)
					if (destPiece.getColor() != p.getColor()) {
						// Trigger flag if piece consumed is GENERAL
						if (destPiece.getPieceType() == XiangqiPieceType.GENERAL) {
							gameEndCheck = true;
						}
						
						// Remove consumed piece from board, and update current piece location
						boardLoc.set(sourceIndex, end);
						boardLoc.remove(i);
						boardPiece.remove(i);
						break;
					} else {
						illegalCheck = true;
						break;
					}
				} 
				// Find no piece at all
				else if (i == boardLoc.size() - 1) boardLoc.set(sourceIndex, end);
			}
		}
		// If not fit to any scenario, consider move illegal
		else illegalCheck = true;
	}
	
	/**
	 * Function to specifically move a GENERAL piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void generalMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0;
		XiangqiPiece p = XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

		int start = source.getRank() * 10 + source.getFile();
		int end = destination.getRank() * 10 + destination.getFile();

		for (i = 0; i < boardLoc.size(); i++) {
			if (boardLoc.get(i) == start) {
				sourceIndex = i;
				p = boardPiece.get(i);
				break;
			}
		}

		// Check whether the end piece match the accepted movement of GENERAL
		// If not, consider move illegal
		if ((destination.getRank() == source.getRank() && (destination.getFile() <= 4) && (destination.getFile() >= 2))
				&& ((destination.getFile() == source.getFile() + 1) || (destination.getFile() == source.getFile() - 1))) {
			for (i = 0; i < boardLoc.size(); i++) {
				if (boardLoc.get(i) == end) {
					XiangqiPiece destPiece = boardPiece.get(i);
					// If destination piece is of other color, move is legal with destination piece consumed
					// Else move illegal (cannot consume piece of same color)
					if (destPiece.getColor() != p.getColor()) {
						// Remove consumed piece from board, and update current piece location
						boardLoc.set(sourceIndex, end);
						boardLoc.remove(i);
						boardPiece.remove(i);
						break;
					} else {
						illegalCheck = true;
						break;
					}
				} 
				// Find no piece at all
				else if (i == boardLoc.size() - 1) boardLoc.set(sourceIndex, end);
			}
		}
		// If not fit to any scenario, consider move illegal
		else illegalCheck = true;
	}
	
	/**
	 * Function to check whether the two Generals are currently facing each other
	 * @return true if the Generals are directly facing each other, else false
	 */
	private boolean checkGenerals() {
		boolean isFacingEachOther;
		int i, redGeneralFile = 0, blackGeneralFile = 0;
		// Go through board to find RED General and BLACK General
		for (i = 0; i < boardPiece.size(); i++) {
			if (boardPiece.get(i).getPieceType() == XiangqiPieceType.GENERAL) {
				if (boardPiece.get(i).getColor() == XiangqiColor.RED) {
					redGeneralFile = boardLoc.get(i) % 10;
				}
				if (boardPiece.get(i).getColor() == XiangqiColor.BLACK) {
					blackGeneralFile = boardLoc.get(i) % 10;
				}
			}
		}
		
		// Check whether the Generals are on same file
		if ((redGeneralFile == blackGeneralFile) && (redGeneralFile != 0)) {
			isFacingEachOther = true;
			// Check for any obstacle between the Generals
			for (i = 2; i <= 4; i++) {
				int tempLoc = i * 10 + redGeneralFile;
				if (boardLoc.contains(tempLoc)) {
					isFacingEachOther = false;
					break;
				}
			}
		} else isFacingEachOther = false;
		
		// If true, then raise flag for end game
		if (isFacingEachOther == true) {
			gameEndCheck = true;
		}
		return isFacingEachOther;
	}
}
