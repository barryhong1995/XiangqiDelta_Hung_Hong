package xiangqi.studenthphong.common;

import xiangqi.common.XiangqiColor;
import xiangqi.common.XiangqiPiece;
import xiangqi.common.XiangqiPieceType;

public class XiangqiPieceImpl implements XiangqiPiece {

	private final XiangqiColor color;
	private final XiangqiPieceType pieceType;
	
	public static XiangqiPiece makePiece(XiangqiPieceType pieceType, XiangqiColor color) {
		return new XiangqiPieceImpl(pieceType, color);
	}
	
	private XiangqiPieceImpl(XiangqiPieceType pieceType, XiangqiColor color) {
		this.pieceType = pieceType;
		this.color = color;
	}
	
	@Override
	public XiangqiColor getColor() {
		return color;
	}

	@Override
	public XiangqiPieceType getPieceType() {
		return pieceType;
	}

}
