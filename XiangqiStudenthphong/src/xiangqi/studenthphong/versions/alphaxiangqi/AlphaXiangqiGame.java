/**
 * 
 */
package xiangqi.studenthphong.versions.alphaxiangqi;

import xiangqi.common.MoveResult;
import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiCoordinate;
import xiangqi.common.XiangqiGame;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;
import xiangqi.studenthphong.common.XiangqiPieceImpl;

/**
 * @author Hung Hong
 *
 */
public class AlphaXiangqiGame implements XiangqiGame {

	private int moveCount;
	private boolean illegalCheck = false;
	
	public AlphaXiangqiGame() {
		moveCount = 0;
	}
	
	/* (non-Javadoc)
	 * @see xiangqi.common.XiangqiGame#makeMove(xiangqi.common.XiangqiCoordinate, xiangqi.common.XiangqiCoordinate)
	 */
	@Override
	public MoveResult makeMove(XiangqiCoordinate source, XiangqiCoordinate destination) {
		if ((source.getRank() != 1) || (source.getFile() != 1)) {
			illegalCheck = true;
			return MoveResult.ILLEGAL;
		}
		else if ((destination.getRank() != 1) || (destination.getFile() != 2)) {
			illegalCheck = true;
			return MoveResult.ILLEGAL;
		}
		else return moveCount++ == 0 ? MoveResult.OK : MoveResult.RED_WINS;
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
		return XiangqiPieceImpl.makePiece(XiangqiPieceType.NONE, XiangqiColor.NONE);
	}

}
