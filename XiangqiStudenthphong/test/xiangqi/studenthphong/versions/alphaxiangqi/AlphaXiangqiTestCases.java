/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/

package xiangqi.studenthphong.versions.alphaxiangqi;

import static org.junit.Assert.*;
import org.junit.*;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.*;
import static xiangqi.studenthphong.testutil.TestCoordinate.makeCoordinate;

/**
 * Test case for Alpha Xiangqi
 */

public class AlphaXiangqiTestCases {
	
	private XiangqiGame game;
	
	@Before
	public void setup() {
		game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.ALPHA_XQ);
	}
	
	@Test
	public void factoryProducesAlphaXiangqiGame() {
		assertNotNull(game);
	}
	
	@Test
	public void redMakesValidFirstMove() {
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 2)));
	}
	
	@Test
	public void blackMakesValidSecondMove() {
		game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 2));
		assertEquals(MoveResult.RED_WINS, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 2)));
	}
	
	@Test
	public void tryToMoveToInvalidLocation() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void tryToMoveFromInvalidLocation() {
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void getPieceAtReturnsNoneNone() {
		final XiangqiPiece p = game.getPieceAt(makeCoordinate(1, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.NONE, p.getPieceType());
		assertEquals(XiangqiColor.NONE, p.getColor());
	}
}