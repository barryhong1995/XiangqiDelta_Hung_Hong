package xiangqi.studenthphong.versions.deltaxiangqi;

import java.util.*;
import java.util.concurrent.CompletionException;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import static xiangqi.common.MoveResult.*;
import static xiangqi.common.XiangqiColor.*;
import static xiangqi.common.XiangqiPieceType.*;
import static xiangqi.studenthphong.common.XiangqiPieceImpl.*;
import static xiangqi.studenthphong.testutil.TestCoordinate.makeCoordinate;

public class DeltaXiangqiGame implements XiangqiGame {
	
	private int round;
	// List of msgCode:
	// 0: Default
	// 1: Input coordinates are out of bound
	// 2: Cannot find piece at source coordinate
	// 3: Illegal move is performed
	private int msgCode;
	private boolean illegalCheck = false;
	private boolean isBeingChecked = false;
	private boolean gameEndCheck;
	private List<XiangqiPiece> boardPiece = new ArrayList<XiangqiPiece>();
	private List<Integer> boardLoc = new ArrayList<Integer>();
	private List<List<Integer>> memoryBoardLoc = new ArrayList<List<Integer>>();
	private List<List<XiangqiPiece>> memoryBoardPiece = new ArrayList<List<XiangqiPiece>>();
	private List<XiangqiColor> memoryBoardPlayer = new ArrayList<XiangqiColor>();

	/*
	 * Initialize board and variables
	 */
	public DeltaXiangqiGame() {
		// Prepare board and fill location list
		// Add red pieces
		boardPiece.add(makePiece(CHARIOT, RED));
		boardPiece.add(makePiece(CHARIOT, RED));
		boardPiece.add(makePiece(HORSE, RED));
		boardPiece.add(makePiece(HORSE, RED));
		boardPiece.add(makePiece(ELEPHANT, RED));
		boardPiece.add(makePiece(ELEPHANT, RED));
		boardPiece.add(makePiece(ADVISOR, RED));
		boardPiece.add(makePiece(ADVISOR, RED));
		boardPiece.add(makePiece(GENERAL, RED));
		boardPiece.add(makePiece(CANNON, RED));
		boardPiece.add(makePiece(CANNON, RED));
		boardPiece.add(makePiece(SOLDIER, RED));
		boardPiece.add(makePiece(SOLDIER, RED));
		boardPiece.add(makePiece(SOLDIER, RED));
		boardPiece.add(makePiece(SOLDIER, RED));
		boardPiece.add(makePiece(SOLDIER, RED));
		
		// Add black pieces
		boardPiece.add(makePiece(SOLDIER, BLACK));
		boardPiece.add(makePiece(SOLDIER, BLACK));
		boardPiece.add(makePiece(SOLDIER, BLACK));
		boardPiece.add(makePiece(SOLDIER, BLACK));
		boardPiece.add(makePiece(SOLDIER, BLACK));
		boardPiece.add(makePiece(CANNON, BLACK));
		boardPiece.add(makePiece(CANNON, BLACK));
		boardPiece.add(makePiece(GENERAL, BLACK));
		boardPiece.add(makePiece(ADVISOR, BLACK));
		boardPiece.add(makePiece(ADVISOR, BLACK));
		boardPiece.add(makePiece(ELEPHANT, BLACK));
		boardPiece.add(makePiece(ELEPHANT, BLACK));
		boardPiece.add(makePiece(HORSE, BLACK));
		boardPiece.add(makePiece(HORSE, BLACK));
		boardPiece.add(makePiece(CHARIOT, BLACK));
		boardPiece.add(makePiece(CHARIOT, BLACK));
		
		// Add board location information (with red perspective)
		// If prompt is about black perspective, consider the array backward
		// Red location
		boardLoc.add(11);
		boardLoc.add(19);
		boardLoc.add(12);
		boardLoc.add(18);
		boardLoc.add(13);
		boardLoc.add(17);
		boardLoc.add(14);
		boardLoc.add(16);
		boardLoc.add(15);
		boardLoc.add(32);
		boardLoc.add(38);
		boardLoc.add(41);
		boardLoc.add(43);
		boardLoc.add(45);
		boardLoc.add(47);
		boardLoc.add(49);
		
		// Black location
		boardLoc.add(79);
		boardLoc.add(77);
		boardLoc.add(75);
		boardLoc.add(73);
		boardLoc.add(71);
		boardLoc.add(88);
		boardLoc.add(82);
		boardLoc.add(105);
		boardLoc.add(106);
		boardLoc.add(104);
		boardLoc.add(107);
		boardLoc.add(103);
		boardLoc.add(108);
		boardLoc.add(102);
		boardLoc.add(109);
		boardLoc.add(101);
		
		// Initialize value for game
		round = 0;
		msgCode = 0;
		gameEndCheck = false;
	}
	
	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		int i;
		List<Integer> tempBoardLoc = new ArrayList<Integer>();
		List<XiangqiPiece> tempBoardPiece = new ArrayList<XiangqiPiece>();
		XiangqiCoordinate newSource, newDest;
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
		// Count turn and decide whose turn
		round++;
		
		// Add all board info into temporary variable for saving purpose
		for (i = 0; i < boardLoc.size(); i++) {
			tempBoardLoc.add(boardLoc.get(i));
			tempBoardPiece.add(boardPiece.get(i));
		}
		
		// Check whether the input coordinates are valid
		// If true, convert input coordinates into usable data
		if (isValidCoordinate(source) && isValidCoordinate(destination)) {
			newSource = turnDecider(source);
			newDest = turnDecider(destination);
		
			// Initialize variables
			p = findPieceAtCoordinate(newSource);
		
			// Attempt to move piece from source coordinate to destination coordinate
			performMovePiece(p, newSource, newDest);
		} else {
			msgCode = 1; // Input Coordinates are out of bound
			illegalCheck = true;
		}
		
		// Check whether move is legal by checking check status
		XiangqiColor oppositeColor;
		if (p.getColor() == RED) oppositeColor = BLACK;
		else oppositeColor = RED;
		
		isBeingChecked = (isCheckedBy(oppositeColor, p.getColor()).getRank() != 0);
		
		if (isBeingChecked) {
			msgCode = 3;
			illegalCheck = true;
			// Recover board config
			boardLoc.clear();
			boardPiece.clear();
			for (i = 0; i < tempBoardLoc.size(); i++) {
				if ((tempBoardLoc.get(i) != null) || (tempBoardPiece.get(i) != null)) {
					boardLoc.add(tempBoardLoc.get(i));
					boardPiece.add(tempBoardPiece.get(i));
				}
			}
		}
		
		// If move is legal, check for result
		if (!illegalCheck) {
			// Record move made
			recordMove(boardLoc, boardPiece, p.getColor());
			// Perform a check of whether GENERALS are facing each other
			boolean generalsFaceEachOther = checkGenerals();
			// Flag for end game is TRUE, return winner
			if ((gameEndCheck) || (isMoveCheckMate(p.getColor()))){
				if (!generalsFaceEachOther) {
					if (round % 2 == 1) { // RED turn
						return RED_WINS;
					}
					else { // BLACK turn
						return BLACK_WINS;
					}
				} else {
					if (round % 2 == 1) { // RED turn
						return BLACK_WINS;
					}
					else { // BLACK turn
						return RED_WINS;
					}
				}
			} else {
				// Perform check for Repetition
				boolean isRepetitive = perpetualCheck();
				if ((isRepetitive) && (round >= 9)) {
					if (round % 2 == 1) { // RED turn
						return BLACK_WINS;
					}
					else { // BLACK turn
						return RED_WINS;
					}
				} else return OK;
			}
		}
		else { // Move is illegal, revert move count and return ILLEGAL
			round--;
			return ILLEGAL;
		}
	}

	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#getPieceAt(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiColor)
	 */
	@Override
	public XiangqiPiece getPieceAt(XiangqiCoordinate where, XiangqiColor aspect) {
		// Initialize variables
		int exactLoc = 0;
		int i;
		CompletionException e = null;
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
		if (isValidCoordinate(where)) {
			// Check for perspective to adjust accordingly
			if (aspect == RED) {
				exactLoc = where.getRank() * 10 + where.getFile();
			} else if (aspect == BLACK) {
				exactLoc = (10 - where.getRank() + 1) * 10 + (9 - where.getFile() + 1);
			}

			// Go through the board to find the piece
			for (i = 0; i < boardLoc.size(); i++) {
				if (boardLoc.get(i) == exactLoc) {
					p = boardPiece.get(i);
					break;
				}
			}
		} else throw new CompletionException(e);
		return p;
	}
	
	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#getMoveMessage()
	 */
	@Override
	public String getMoveMessage() {
		String msg;
		if (illegalCheck == true) {
			switch (msgCode) {
			case 1: {
				msg = "Input Coordinates are out of bound";
				break;
			}
			case 2: {
				msg = "Cannot find piece at Source coordinates";
				break;
			}
			case 3: {
				msg = "Move is invalid. Try again";
				break;
			}
			default: {
				msg = "Illegal Action is performed";
				break;
			}
			}
		}
		else msg = "";
		return msg;
	}

	/**
	 * Function to check whether a Coordinate is valid
	 * @param coord coordinate that need to be checked
	 * @return true if valid, else false
	 */
	private boolean isValidCoordinate(XiangqiCoordinate coord) {
		if ((coord.getRank() >= 1) && (coord.getRank() <= 10) && (coord.getFile() >= 1) && (coord.getFile() <= 9)) {
			return true;
		} 
		else {
			return false;
		}
	}
	
	/**
	 * Function to maneuver coordinate to match with respective player's point of view
	 * @param coordinate that is perceived by player
	 * @return coordinate that is controlled by fixed, single perspective
	 */
	private XiangqiCoordinate turnDecider(XiangqiCoordinate coordinate) {
		XiangqiCoordinate result = coordinate;
		if (round % 2 == 0) { // BLACK turn
			result = makeCoordinate(10 - coordinate.getRank() + 1, 9 - coordinate.getFile() + 1);
		}
		return result;
	}
	
	/**
	 * Function to determine the type of the piece at source coordinate
	 * @param source coordinate of the piece
	 * @return type of the starting piece
	 */
	private XiangqiPiece findPieceAtCoordinate(XiangqiCoordinate source) {
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
		int i;
		int start = source.getRank() * 10 + source.getFile();
		illegalCheck = true;
		// Go through the board to determine the starting piece
				// If no piece in source location, consider move illegal
				for (i = 0; i < boardLoc.size(); i++) {
					if (boardLoc.get(i) == start) {
						// Check that the player use piece of correct color
						if (((round % 2 == 1) && (boardPiece.get(i).getColor() == RED))
						 || ((round % 2 == 0) && (boardPiece.get(i).getColor() == BLACK))) {
							illegalCheck = false;
							p = boardPiece.get(i);
							break;
						} else {
							msgCode = 2; // Cannot find piece at source coordinate
							illegalCheck = true;
							break;
						}
					}
				}
		return p;
	}
	
	/**
	 * Function to check whether a checkmate scenario has occured
	 * @param attackColor the color of the attacking player
	 * @return true if the scenario fits, else false
	 */
	private boolean isMoveCheckMate(XiangqiColor attackColor) {
		XiangqiColor defendColor;
		int i, j;
		boolean canBeDefended = false, tempIllegalCheck;
		XiangqiCoordinate checkPieceCoordinate;
		List<Integer> tempBoardLoc = new ArrayList<Integer>();
		List<XiangqiPiece> tempBoardPiece = new ArrayList<XiangqiPiece>();
		
		// Determine who is attacking and who is defending
		if (attackColor == RED) defendColor = BLACK;
		else defendColor = RED;
		
		// Add all board info into temporary variable
		for (j = 0; j < boardLoc.size(); j++) {
			tempBoardLoc.add(boardLoc.get(j));
			tempBoardPiece.add(boardPiece.get(j));
		}
		
		// If it is being checked, find if there is any way to defend it
		checkPieceCoordinate = isCheckedBy(attackColor, defendColor);
		if (checkPieceCoordinate.getRank() != 0) {
			// Find if checking piece can be taken out by any piece on the board
			for (i = 0; i < boardLoc.size(); i++) {
				if (boardPiece.get(i).getColor() == defendColor) {
					// Find all possible coordinates for movement
					for (int rank = 1; rank <= 10; rank++) {
						for (int file = 1; file <= 9; file++) {
							XiangqiCoordinate testCoordinate = convertToCoordinate(rank * 10 + file);
							tempIllegalCheck = illegalCheck;
							illegalCheck = false;
							// Try to move the piece and see if it is no more checked
							switch (boardPiece.get(i).getPieceType()) {
							case CHARIOT: {
								if (validChariotMove(convertToCoordinate(boardLoc.get(i)), testCoordinate)) {
									performMovePiece(boardPiece.get(i), convertToCoordinate(boardLoc.get(i)), testCoordinate);
									if (isCheckedBy(attackColor, defendColor).getRank() == 0) {
										canBeDefended = true;
									}
								}
								break;
							}
							case HORSE: {
								if (validHorseMove(convertToCoordinate(boardLoc.get(i)), testCoordinate)) {
									performMovePiece(boardPiece.get(i), convertToCoordinate(boardLoc.get(i)), testCoordinate);
									if (isCheckedBy(attackColor, defendColor).getRank() == 0) {
										canBeDefended = true;
									}
								}
								break;
							}
							case SOLDIER: {
								if (validSoldierMove(convertToCoordinate(boardLoc.get(i)), testCoordinate, defendColor)) {
									performMovePiece(boardPiece.get(i), convertToCoordinate(boardLoc.get(i)), testCoordinate);
									if (isCheckedBy(attackColor, defendColor).getRank() == 0) {
										canBeDefended = true;
									}
								}
								break;
							}
							case CANNON: {
								if (validCannonMove(convertToCoordinate(boardLoc.get(i)), testCoordinate) == 1) {
									performMovePiece(boardPiece.get(i), convertToCoordinate(boardLoc.get(i)), testCoordinate);
									if (isCheckedBy(attackColor, defendColor).getRank() == 0) {
										canBeDefended = true;
									}
								}
								break;
							}
							case ADVISOR:
								if (validAdvisorMove(convertToCoordinate(boardLoc.get(i)), testCoordinate)) {
									if ((rank == 9) || (file == 5)) {};
									performMovePiece(boardPiece.get(i), convertToCoordinate(boardLoc.get(i)), testCoordinate);
									if (isCheckedBy(attackColor, defendColor).getRank() == 0) {
										canBeDefended = true;
									}
								}
								break;
							case ELEPHANT:
								if (validElephantMove(convertToCoordinate(boardLoc.get(i)), testCoordinate, defendColor)) {
									performMovePiece(boardPiece.get(i), convertToCoordinate(boardLoc.get(i)), testCoordinate);
									if (isCheckedBy(attackColor, defendColor).getRank() == 0) {
										canBeDefended = true;
									}
								}
								break;
							case GENERAL:
								if (validGeneralMove(convertToCoordinate(boardLoc.get(i)), testCoordinate)) {
									performMovePiece(boardPiece.get(i), convertToCoordinate(boardLoc.get(i)), testCoordinate);
									if (isCheckedBy(attackColor, defendColor).getRank() == 0) {
										canBeDefended = true;
									}
								}
								break;
							default:
								break;
							}
							
							// Recover boardLoc and boardPiece data
							illegalCheck = tempIllegalCheck;
							boardLoc.clear();
							boardPiece.clear();
							for (j = 0; j < tempBoardLoc.size(); j++) {
								if ((tempBoardLoc.get(j) != null) || (tempBoardPiece.get(j) != null)) {
									boardLoc.add(tempBoardLoc.get(j));
									boardPiece.add(tempBoardPiece.get(j));
								}
							}	
							// If a check can be defended, break out of the loop
							if (canBeDefended) break;
						}
						// If a check can be defended, break out of the loop
						if (canBeDefended) break;
					}
					// If a check can be defended, break out of the loop
					if (canBeDefended) break;
				}
			}
		} else return false; // It is not check-mate
		
		return !canBeDefended;
	}
	
	/**
	 * Function to check whether one color is currently checking another
	 * @param attackColor the color of attacking player
	 * @param defendColor the color of defending player
	 * @return Coordinate of the checking piece
	 */
	private XiangqiCoordinate isCheckedBy(XiangqiColor attackColor, XiangqiColor defendColor) {
		int i, generalLoc = 0;
		// Locate the location of GENERAL for defending color
		for (i = 0; i < boardLoc.size(); i++) {
			if ((boardPiece.get(i).getColor() == defendColor) && (boardPiece.get(i).getPieceType() == GENERAL)) {
				generalLoc = boardLoc.get(i);
				break;
			}
		}
		
		// Test all piece of attacking color and see whether it is in check (piece is in ready position to take the General
		for (i = 0; i < boardLoc.size(); i++) {
			if (boardPiece.get(i).getColor() == attackColor) {
				switch (boardPiece.get(i).getPieceType()) {
				case CHARIOT: {
					if (validChariotMove(convertToCoordinate(boardLoc.get(i)), convertToCoordinate(generalLoc))) {
						return convertToCoordinate(boardLoc.get(i));
					}
					break;
				}
				case HORSE: {
					if (validHorseMove(convertToCoordinate(boardLoc.get(i)), convertToCoordinate(generalLoc))) {
						return convertToCoordinate(boardLoc.get(i));
					}
					break;
				}
				case SOLDIER: {
					if (validSoldierMove(convertToCoordinate(boardLoc.get(i)), convertToCoordinate(generalLoc), attackColor)) {
						return convertToCoordinate(boardLoc.get(i));
					}
					break;
				}
				case CANNON: {
					if (validCannonMove(convertToCoordinate(boardLoc.get(i)), convertToCoordinate(generalLoc)) == 1) {
						return convertToCoordinate(boardLoc.get(i));
					}
					break;
				}
				default:
					break;
				}
			}
		}
		
		// If not, return false coordinate (defending color is not being checked)
		return convertToCoordinate(0);
	}
	
	/**
	 * Function to convert a number into data of XiangqiCoordinate type
	 * @param loc integer value representing coordinate
	 * @return actual coordinate
	 */
	private XiangqiCoordinate convertToCoordinate(int loc) {
		int rank = loc / 10;
		int file = loc % 10;
		return makeCoordinate(rank, file);
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
			case CANNON : {
				cannonMove(source, destination);
				break;
			}
			case ELEPHANT : {
				elephantMove(source, destination);
				break;
			}
			case HORSE : {
				horseMove(source, destination);
				break;
			}
			case ADVISOR : {
				advisorMove(source, destination);
				break;
			}
			case GENERAL : {
				generalMove(source, destination);
				break;
			}
			case SOLDIER : {
				soldierMove(source, destination);
				break;
			}
			default:
				break;
			}
		}
	}
	
	/**
	 * Function to check whether a CHARIOT move is valid
	 * @param source coordinate of CHARIOT piece
	 * @param destination coordinate of CHARIOT piece
	 * @return true if valid, else false
	 */
	private boolean validChariotMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		int tempLoc, i;
		boolean validCheck = true;
		
		// Same rank scenario
		if (source.getRank() == destination.getRank()) {
			// Case of source file less than destination file (moving right)
			if (source.getFile() < destination.getFile()) {
				// Check for any obstacle along the way
				for (i = source.getFile(); i < destination.getFile(); i++) {
					tempLoc = source.getRank() * 10 + i;
					if ((boardLoc.contains(tempLoc)) && (i != source.getFile())) {
						validCheck = false;
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
						validCheck = false;
						break;
					}
				}
			}
			// If not (source file = destination file), consider move illegal
			else {
				validCheck = false;
			}
		}

		// Same file scenario
		else if (source.getFile() == destination.getFile()) {
			// Case of source rank less than destination rank (moving up)
			if (source.getRank() < destination.getRank()) {
				// Check for any obstacle along the way
				for (i = source.getRank(); i < destination.getRank(); i++) {
					tempLoc = i * 10 + source.getFile();
					if ((boardLoc.contains(tempLoc)) && (i != source.getRank())) {
						validCheck = false;
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
						validCheck = false;
						break;
					}
				}
			}
		}

		// If not fit to any scenario, consider move illegal
		else {
			validCheck = false;
		}
		return validCheck;
	}
	
	/**
	 * Function to specifically move a CHARIOT piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void chariotMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0;
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);

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
		if (!validChariotMove(source, destination)) {
			msgCode = 3; // Illegal move is performed
			illegalCheck = true;
		}

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
						if (destPiece.getPieceType() == GENERAL) {
							gameEndCheck = true;
						}
						
						// Remove consumed piece from board, and update current piece location
						boardLoc.set(sourceIndex, end);
						boardLoc.remove(i);
						boardPiece.remove(i);
						break;
					} else {
						msgCode = 3; // Illegal move is performed
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
	 * Function to check whether a CANNON move is valid
	 * @param source coordinate of CANNON piece
	 * @param destination coordinate of CANNON piece
	 * @return number of obstacle in between, or -1 for invalid
	 */
	private int validCannonMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		int tempLoc, i, countObstacle = 0;
		
		// Same rank scenario
		if (source.getRank() == destination.getRank()) {
			// Case of source file less than destination file (moving right)
			if (source.getFile() < destination.getFile()) {
				// Check for any obstacle along the way, only allowing 1 obstacle in between
				for (i = source.getFile(); i < destination.getFile(); i++) {
					tempLoc = source.getRank() * 10 + i;
					if ((boardLoc.contains(tempLoc)) && (i != source.getFile())) {
						countObstacle++;
					}
				}
			}

			// Case of source file more than destination file (moving left)
			else if (source.getFile() > destination.getFile()) {
				// Check for any obstacle along the way
				for (i = source.getFile(); i > destination.getFile(); i--) {
					tempLoc = source.getRank() * 10 + i;
					if ((boardLoc.contains(tempLoc)) && (i != source.getFile())) {
						countObstacle++;
					}
				}
			}
			// If not (source file = destination file), consider move illegal
			else {
				countObstacle = -1;
			}
		}
		
		// Same file scenario
		else if (source.getFile() == destination.getFile()) {
			// Case of source rank less than destination rank (moving up)
			if (source.getRank() < destination.getRank()) {
				// Check for any obstacle along the way
				for (i = source.getRank(); i < destination.getRank(); i++) {
					tempLoc = i * 10 + source.getFile();
					if ((boardLoc.contains(tempLoc)) && (i != source.getRank())) {
						countObstacle++;
					}
				}
			}

			// Case of source rank more than destination rank (moving down)
			else if (source.getRank() > destination.getRank()) {
				// Check for any obstacle along the way
				for (i = source.getRank(); i > destination.getRank(); i--) {
					tempLoc = i * 10 + source.getFile();
					if ((boardLoc.contains(tempLoc)) && (i != source.getRank())) {
						countObstacle++;
					}
				}
			}
		}
		// If not fit to any scenario, consider move illegal
		else countObstacle = -1;
		
		 return countObstacle;
	}
	
	/**
	 * Function to specifically move a CANNON piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void cannonMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0, countObstacle = 0;
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

		int start = source.getRank() * 10 + source.getFile();
		int end = destination.getRank() * 10 + destination.getFile();

		for (i = 0; i < boardLoc.size(); i++) {
			if (boardLoc.get(i) == start) {
				sourceIndex = i;
				p = boardPiece.get(i);
				break;
			}
		}
		
		// Check whether the end piece match the accepted movement of CANNON
		// If not, consider move illegal
		countObstacle = validCannonMove(source, destination);
		if (countObstacle == -1) {
			msgCode = 3; // Illegal move is performed
			illegalCheck = true;
		}
		
		// Now check further for the destination location
		if (illegalCheck == false) {
			// Check whether there is a piece at destination location and there is 1 obstacle in between
			for (i = 0; i < boardLoc.size(); i++) {
				if (boardLoc.get(i) == end) {
					// Accept taking piece only if the number of obstacle in between is 1, else consider move illegal
					if (countObstacle == 1) {
						XiangqiPiece destPiece = boardPiece.get(i);
	
						// If destination piece is of other color, move is legal with destination piece consumed
						// Else move illegal (cannot consume piece of same color)
						if (destPiece.getColor() != p.getColor()) {
							// Trigger flag if piece consumed is GENERAL
							if (destPiece.getPieceType() == GENERAL) {
								gameEndCheck = true;
							}
							
							// Remove consumed piece from board, and update current piece location
							boardLoc.set(sourceIndex, end);
							boardLoc.remove(i);
							boardPiece.remove(i);
							break;
						} else {
							msgCode = 3; // Illegal move is performed
							illegalCheck = true;
							break;
						}
					} else {
						msgCode = 3; // Illegal move is performed
						illegalCheck = true;
						break;
					}
				} 
				// Find no piece at all and the iterator is at the end, check with no obstacle in between
				else if (i == boardLoc.size() - 1) {
					if (countObstacle == 0) boardLoc.set(sourceIndex, end);
					else {
						msgCode = 3; // Illegal move is performed
						illegalCheck = true;
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Function to check whether a HORSE move is valid
	 * @param source coordinate of HORSE piece
	 * @param destination coordinate of HORSE piece
	 * @return true if valid, else false
	 */
	private boolean validHorseMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		int tempLoc;
		boolean validCheck = true;
		// Moving up
		if ((destination.getRank() == source.getRank() + 2) && ((destination.getFile() == source.getFile() + 1) || (destination.getFile() == source.getFile() - 1))) {
			// Check for any obstacle blocking the jump
			tempLoc = (source.getRank() + 1) * 10 + source.getFile();
			if (boardLoc.contains(tempLoc)) {
				validCheck = false;
			}
		}
		
		// Moving down
		else if ((destination.getRank() == source.getRank() - 2) && ((destination.getFile() == source.getFile() + 1) || (destination.getFile() == source.getFile() - 1))) {
			// Check for any obstacle blocking the jump
			tempLoc = (source.getRank() - 1) * 10 + source.getFile();
			if (boardLoc.contains(tempLoc)) {
				validCheck = false;
			}
		}
		
		// Moving left
		else if ((destination.getFile() == source.getFile() - 2) && ((destination.getRank() == source.getRank() + 1) || (destination.getRank() == source.getRank() - 1))) {
			// Check for any obstacle blocking the jump
			tempLoc = source.getRank() * 10 + source.getFile() - 1;
			if (boardLoc.contains(tempLoc)) {
				validCheck = false;
			}
		}
		
		// Moving right
		else if ((destination.getFile() == source.getFile() + 2) && ((destination.getRank() == source.getRank() + 1) || (destination.getRank() == source.getRank() - 1))) {
			// Check for any obstacle blocking the jump
			tempLoc = source.getRank() * 10 + source.getFile() + 1;
			if (boardLoc.contains(tempLoc)) {
				validCheck = false;
			}
		}
		
		// If not fit to any scenario, consider move illegal
		else validCheck = false;
		
		return validCheck;
	}
	
	/**
	 * Function to specifically move a HORSE piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void horseMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0;
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

		int start = source.getRank() * 10 + source.getFile();
		int end = destination.getRank() * 10 + destination.getFile();

		for (i = 0; i < boardLoc.size(); i++) {
			if (boardLoc.get(i) == start) {
				sourceIndex = i;
				p = boardPiece.get(i);
				break;
			}
		}
		
		// Check whether the end piece match the accepted movement of HORSE
		// If not, consider move illegal
		if (!validHorseMove(source, destination)) {
			msgCode = 3; // Illegal move is performed
			illegalCheck = true;
		}
		
		// Now further check for destination
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
						if (destPiece.getPieceType() == GENERAL) {
							gameEndCheck = true;
						}
						
						// Remove consumed piece from board, and update current piece location
						boardLoc.set(sourceIndex, end);
						boardLoc.remove(i);
						boardPiece.remove(i);
						break;
					} else {
						msgCode = 3; // Illegal move is performed
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
	 * Function to check whether an ELEPHANT move is valid
	 * @param source coordinate of ELEPHANT piece
	 * @param destination coordinate of ELEPHANT piece
	 * @param color color of ELEPHANT piece
	 * @return true if valid, else false
	 */
	private boolean validElephantMove(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiColor color) {
		int i, tempLoc, count = 0;
		boolean validCheck = true;
		// Moving in a line with positive gradient
		if ((destination.getRank() - source.getRank() == destination.getFile() - source.getFile()) && (Math.abs(destination.getRank() - source.getRank()) == 2)) {
			// Case of source file less than destination file (up right)
			if (source.getFile() < destination.getFile()) {
				// Check for any obstacle along the way
				count = 0;
				for (i = source.getFile(); i < destination.getFile(); i++) {
					tempLoc = (source.getRank() + count) * 10 + i;
					count++;
					if ((boardLoc.contains(tempLoc)) && (i != source.getFile())) {
						validCheck = false;
						break;
					}
				}
			}

			// Case of source file more than destination file (down left)
			else if (source.getFile() > destination.getFile()) {
				// Check for any obstacle along the way
				count = 0;
				for (i = source.getFile(); i > destination.getFile(); i--) {
					tempLoc = (source.getRank() - count) * 10 + i;
					count++;
					if ((boardLoc.contains(tempLoc)) && (i != source.getFile())) {
						validCheck = false;
						break;
					}
				}
			}
		}

		// Moving in a line with negative gradient
		else if ((destination.getRank() - source.getRank() == source.getFile() - destination.getFile()) && (Math.abs(destination.getRank() - source.getRank()) == 2)) {
			// Case of source rank less than destination rank (up left)
			if (source.getRank() < destination.getRank()) {
				// Check for any obstacle along the way
				count = 0;
				for (i = source.getRank(); i < destination.getRank(); i++) {
					tempLoc = i * 10 + source.getFile() - count;
					count++;
					if ((boardLoc.contains(tempLoc)) && (i != source.getRank())) {
						validCheck = false;
						break;
					}
				}
			}

			// Case of source rank more than destination rank (down right)
			else if (source.getRank() > destination.getRank()) {
				// Check for any obstacle along the way
				count = 0;
				for (i = source.getRank(); i > destination.getRank(); i--) {
					tempLoc = i * 10 + source.getFile() + count;
					count++;
					if ((boardLoc.contains(tempLoc)) && (i != source.getRank())) {
						validCheck = false;
						break;
					}
				}
			}
		}
		// If not fit to any scenario, consider move illegal
		else validCheck = false;
		
		// Further check whether the ELEPHANT attempt to cross the river or start from illegal position
		if (validCheck == true) {
			if (((color == RED) && ((source.getRank() > 5) || (destination.getRank() > 5)))
			|| ((color == BLACK) && ((source.getRank() < 6) || (destination.getRank() < 6)))) {
				validCheck = false;
			}
		}
		return validCheck;
	}
	
	/**
	 * Function to specifically move an ELEPHANT piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void elephantMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0;
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

		int start = source.getRank() * 10 + source.getFile();
		int end = destination.getRank() * 10 + destination.getFile();

		for (i = 0; i < boardLoc.size(); i++) {
			if (boardLoc.get(i) == start) {
				sourceIndex = i;
				p = boardPiece.get(i);
				break;
			}
		}
		
		// Check whether the end piece match the accepted movement of ELEPHANT (diagonal)
		// If not, consider move illegal
		if (!validElephantMove(source, destination, p.getColor())) {
			msgCode = 3; // Illegal move is performed
			illegalCheck = true;
		}
		
		// Now check further for the destination location
		if (illegalCheck == false) {
			// Check whether there is a piece at destination location
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
						msgCode = 3; // Illegal move is performed
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
	 * Function to check whether an ADVISOR move is valid
	 * @param source coordinate of ADVISOR piece
	 * @param destination coordinate of ADVISOR piece
	 * @return true if valid, else false
	 */
	private boolean validAdvisorMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		boolean validCheck = false;
		if (((destination.getRank() == source.getRank() + 1) && (destination.getFile() == source.getFile() - 1)    // Diag. UP & LEFT 
		  || (destination.getRank() == source.getRank() + 1) && (destination.getFile() == source.getFile() + 1)    // Diag. UP & RIGHT
		  || (destination.getRank() == source.getRank() - 1) && (destination.getFile() == source.getFile() - 1)    // Diag. DOWN & LEFT
		  || (destination.getRank() == source.getRank() - 1) && (destination.getFile() == source.getFile() + 1))   // Diag. DOWN & RIGHT
		 && ((((destination.getRank() <= 3) && (destination.getRank() >= 1)) || ((destination.getRank() <= 10) && (destination.getRank() >= 8)))
				 && (destination.getFile() >= 4) && (destination.getFile() <= 6))) { // Within the palace area (RANK in 1 - 3, FILE in 4 - 6) {
			validCheck = true;
		}
		
		return validCheck;
	}
	
	/**
	 * Function to specifically move an ADVISOR piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void advisorMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0;
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

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
		
		if (validAdvisorMove(source, destination)) {
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
						msgCode = 3; // Illegal move is performed
						illegalCheck = true;
						break;
					}
				} 
				// Find no piece at all
				else if (i == boardLoc.size() - 1) boardLoc.set(sourceIndex, end);
			}
		}
		// If not fit to any scenario, consider move illegal
		else {
			msgCode = 3; // Illegal move is performed
			illegalCheck = true;
		}
	}
	
	/**
	 * Function to check whether an SOLDIER move is valid
	 * @param source coordinate of SOLDIER piece
	 * @param destination coordinate of SOLDIER piece
	 * @param color color of SOLDIER piece
	 * @return true if valid, else false
	 */
	private boolean validSoldierMove(XiangqiCoordinate source, XiangqiCoordinate destination, XiangqiColor color) {
		boolean validCheck = false;
		if ((((destination.getRank() == source.getRank() + 1) && (destination.getFile() == source.getFile()) && (color == RED))
		 ||  ((destination.getRank() == source.getRank() - 1) && (destination.getFile() == source.getFile()) && (color == BLACK)))
		 || (((destination.getFile() == source.getFile() + 1) && (destination.getRank() == source.getRank()) && (destination.getRank() >= 5) && (destination.getRank() <= 6))
		 ||  ((destination.getFile() == source.getFile() - 1) && (destination.getRank() == source.getRank()) && (destination.getRank() >= 5) && (destination.getRank() <= 6)))) {
			validCheck = true;
		}
		
		return validCheck;
	}
	
	/**
	 * Function to specifically move a SOLDIER piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void soldierMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0;
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

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
		if (validSoldierMove(source, destination, p.getColor())){
			for (i = 0; i < boardLoc.size(); i++) {
				if (boardLoc.get(i) == end) {
					XiangqiPiece destPiece = boardPiece.get(i);
					// If destination piece is of other color, move is legal with destination piece consumed
					// Else move illegal (cannot consume piece of same color)
					if (destPiece.getColor() != p.getColor()) {
						// Trigger flag if piece consumed is GENERAL
						if (destPiece.getPieceType() == GENERAL) {
							gameEndCheck = true;
						}
						
						// Remove consumed piece from board, and update current piece location
						boardLoc.set(sourceIndex, end);
						boardLoc.remove(i);
						boardPiece.remove(i);
						break;
					} else {
						msgCode = 3; // Illegal move is performed
						illegalCheck = true;
						break;
					}
				} 
				// Find no piece at all
				else if (i == boardLoc.size() - 1) boardLoc.set(sourceIndex, end);
			}
		}
		// If not fit to any scenario, consider move illegal
		else {
			msgCode = 3; // Illegal move is performed
			illegalCheck = true;
		}
	}
	
	/**
	 * Function to check whether a GENERAL move is valid
	 * @param source coordinate of GENERAL piece
	 * @param destination coordinate of GENERAL piece
	 * @return true if valid, else false
	 */
	private boolean validGeneralMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		boolean validCheck = false;
		if (((destination.getRank() == source.getRank() && (destination.getFile() <= 6) && (destination.getFile() >= 4))
				&& ((destination.getFile() == source.getFile() + 1) || (destination.getFile() == source.getFile() - 1)))
		 || ((destination.getFile() == source.getFile() && (((destination.getRank() <= 3) && (destination.getRank() >= 1)) 
				 										|| ((destination.getRank() <= 10) && (destination.getRank() >= 8))))
				&& ((destination.getRank() == source.getRank() + 1) || (destination.getRank() == source.getRank() - 1)))) {
			validCheck = true;
		}
		
		return validCheck;
	}
	
	/**
	 * Function to specifically move a GENERAL piece
	 * @param source - starting coordinate of the piece
	 * @param destination - ending coordinate for the piece
	 */
	private void generalMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		// Initialize necessary variables
		int i, sourceIndex = 0;
		XiangqiPiece p = makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);;

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
		if (validGeneralMove(source, destination)){
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
						msgCode = 3; // Illegal move is performed
						illegalCheck = true;
						break;
					}
				} 
				// Find no piece at all
				else if (i == boardLoc.size() - 1) boardLoc.set(sourceIndex, end);
			}
		}
		// If not fit to any scenario, consider move illegal
		else {
			msgCode = 3; // Illegal move is performed
			illegalCheck = true;
		}
	}
	
	/**
	 * Function to check whether the two Generals are currently facing each other
	 * @return true if the Generals are directly facing each other, else false
	 */
	private boolean checkGenerals() {
		boolean isFacingEachOther;
		int i, redGeneralFile = 0, blackGeneralFile = 0, redRank = 0, blackRank = 0;
		// Go through board to find RED General and BLACK General
		for (i = 0; i < boardPiece.size(); i++) {
			if (boardPiece.get(i).getPieceType() == GENERAL) {
				if (boardPiece.get(i).getColor() == RED) {
					redGeneralFile = boardLoc.get(i) % 10;
					redRank = boardLoc.get(i) / 10;
				}
				if (boardPiece.get(i).getColor() == BLACK) {
					blackGeneralFile = boardLoc.get(i) % 10;
					blackRank = boardLoc.get(i) / 10;
				}
			}
		}
		
		// Check whether the Generals are on same file
		if ((redGeneralFile == blackGeneralFile) && (redGeneralFile != 0)) {
			isFacingEachOther = true;
			// Check for any obstacle between the Generals
			for (i = redRank + 1; i <= blackRank - 1; i++) {
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

	/**
	 * Perform a check from the memory to the latest 5 move and check if there are 
	 * @return
	 */
	private boolean perpetualCheck() {
		int i, j, count;
		boolean initialCheck = false;
		// Check whether each player has made at least 5 moves
		if (memoryBoardLoc.size() >= 9) {
			for (i = memoryBoardLoc.size() - 9; i < memoryBoardLoc.size(); i++) {
				count = 0;
				for (j = memoryBoardLoc.size() - 9; j < memoryBoardLoc.size(); j++) {
					if ((memoryBoardLoc.get(i).equals(memoryBoardLoc.get(j))) 
					&& (memoryBoardPiece.get(i).equals(memoryBoardPiece.get(j))) 
					&& (memoryBoardPlayer.get(i) == memoryBoardPlayer.get(j))) {
						count++;
					}
				}
				if (count >= 3) {
					initialCheck = true;
				}
			}
		}
		
		return initialCheck;
	}

	/**
	 * Record move made and save into memory array
	 * @param savedboardPiece Configuration for pieces
	 * @param savedboardLoc Configuration for coordinates
	 * @param xiangqiColor Player who made the move
	 */
	private void recordMove(List<Integer> savedBoardLoc, List<XiangqiPiece> savedBoardPiece, XiangqiColor xiangqiColor) {
		List<Integer> tempBoardLoc = new ArrayList<Integer>();
		List<XiangqiPiece> tempBoardPiece = new ArrayList<XiangqiPiece>();
		for (int i = 0; i < savedBoardPiece.size(); i++) {
			tempBoardLoc.add(savedBoardLoc.get(i));
			tempBoardPiece.add(savedBoardPiece.get(i));
		}
		memoryBoardPiece.add(tempBoardPiece);
		memoryBoardLoc.add(tempBoardLoc);
		memoryBoardPlayer.add(xiangqiColor);
	}
}

