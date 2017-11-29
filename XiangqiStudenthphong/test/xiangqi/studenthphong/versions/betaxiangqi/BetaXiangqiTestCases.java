/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/

package xiangqi.studenthphong.versions.betaxiangqi;

import static org.junit.Assert.*;
import org.junit.*;

import xiangqi.XiangqiGameFactory;
import xiangqi.common.*;

import static xiangqi.studenthphong.testutil.TestCoordinate.makeCoordinate;

/**
 * Test case for Beta Xiangqi
 */

public class BetaXiangqiTestCases {
	
	private XiangqiGame game;
	
	// TODO Test Cases
	// 1. Test for factory
	// 2. Test for correct initial location of pieces (Red perspective)
	// 3. Test for correct initial location of pieces (Black perspective)
	// 4. Try to make valid move (Chariot)
	// 5. Try to make invalid move (Chariot)
	// 6. Try to make valid move (Advisor)
	// 7. Try to make invalid move (Advisor)
	// 8. Try to make valid move (Soldier)
	// 9. Try to make invalid move (Soldier)
	// 10. Try to make valid move (General)
	// 11. Try to make invalid move (General)
	// 12. Try to make invalid move from square with no piece
	// 13. Try to make invalid move of using piece of wrong color
	// 14. Test for getPieceAt method
	// 15. Test for result decision (General is taken - RED wins)
	// 16. Test for result decision (General is taken - BLACK wins)
	// 17. Test for result decision (Generals are facing each other - BLACK wins)
	// 18. Test for result decision (Generals are facing each other - RED wins)
	// 19. Test for result decision (Draw)
	
	@Before
	public void setup() {
		game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.BETA_XQ);
	}
	
	@Test
	public void factoryProducesBetaXiangqiGame() {
		assertNotNull(game);
	}
	
	@Test
	public void testCorrectInitialLocationPiecesRedPerspective() {
		XiangqiPiece p;
		
		// Check for initial location of red pieces, in red perspective
		// CHARIOT
		p = game.getPieceAt(makeCoordinate(1, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(1, 5), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());	
		// ADVISOR
		p = game.getPieceAt(makeCoordinate(1, 2), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(1, 4), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// GENERAL
		p = game.getPieceAt(makeCoordinate(1, 3), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// SOLDIER
		p = game.getPieceAt(makeCoordinate(2, 3), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		
		// Check for initial location of black pieces, in red perspective
		// CHARIOT
		p = game.getPieceAt(makeCoordinate(5, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(5, 5), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// ADVISOR
		p = game.getPieceAt(makeCoordinate(5, 2), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(5, 4), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// GENERAL
		p = game.getPieceAt(makeCoordinate(5, 3), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// SOLDIER
		p = game.getPieceAt(makeCoordinate(4, 3), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
	}
	
	@Test
	public void testCorrectInitialLocationPiecesBlackPerspective() {
		XiangqiPiece p;
		
		// Check for initial location of black pieces, in black perspective
		// CHARIOT
		p = game.getPieceAt(makeCoordinate(1, 1), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(1, 5), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// ADVISOR
		p = game.getPieceAt(makeCoordinate(1, 2), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(1, 4), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// GENERAL
		p = game.getPieceAt(makeCoordinate(1, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// SOLDIER
		p = game.getPieceAt(makeCoordinate(2, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		
		// Check for initial location of red pieces, in black perspective
		// CHARIOT
		p = game.getPieceAt(makeCoordinate(5, 1), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(5, 5), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// ADVISOR
		p = game.getPieceAt(makeCoordinate(5, 2), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(5, 4), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// GENERAL
		p = game.getPieceAt(makeCoordinate(5, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// SOLDIER
		p = game.getPieceAt(makeCoordinate(4, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
	}
	
	@Test
	public void testValidMoveChariot() {
		// Move up - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(4, 1)));
		// Move up - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(4, 1)));
		
		// Move down - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 1), makeCoordinate(3, 1)));
		// Move down - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 1), makeCoordinate(3, 1)));
		
		// Move right - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3)));
		// Move right - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 2)));
		
		// Move left - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 3), makeCoordinate(3, 2)));
		// Move left - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 2), makeCoordinate(3, 1)));
		
		// Take another piece - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 2), makeCoordinate(5, 2)));
		// Take another piece - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(5, 1)));
	}
	
	@Test
	public void testInvalidMoveChariot() {
		// RED turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// RED turn - try to move to destination that has obstacle in between (horizontal)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// RED turn - same source and destination coordination (no movement)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// RED turn - try to move not following the rule for Chariot
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// BLACK turn - try to move to destination that has obstacle in between (horizontal)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// BLACK turn - same source and destination coordination (no movement)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// BLACK turn - try to move not following the rule for Chariot
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(4, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 5))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move to destination that has obstacle in between (vertical)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 1), makeCoordinate(2, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 1), makeCoordinate(4, 2))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 5), makeCoordinate(3, 3))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 2), makeCoordinate(4, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to move to destination that has obstacle in between (vertical)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(3, 3), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testValidMoveAdvisor() {
		// Move diag. up and left - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
		// Move diag. up and left - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
		
		// Move diag. up and right - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(3, 2)));
		// Move diag. up and right - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(3, 2)));
		
		// Move diag. down and left - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 2), makeCoordinate(2, 1)));
		// Move diag. down and left - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 2), makeCoordinate(2, 1)));
		
		// Move diag. down and right - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 2)));
		// Move diag. down and right - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 2)));
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(3, 2)));
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(3, 2)));
		
		// Take another piece - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 2), makeCoordinate(4, 3)));
		
		// Take another piece - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 2), makeCoordinate(4, 3)));
	}
	
	@Test
	public void testInvalidMoveAdvisor() {
		// RED turn - try to move not following the rule for Advisor
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move not following the rule for Advisor
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(3, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 2))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 2))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testValidMoveSoldier() {
		// Move up - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		
		// Move up and take another piece - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
	}
	
	@Test
	public void testInvalidMoveSoldier() {
		// RED turn - try to move not following the rule for Soldier
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move not following the rule for Soldier
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 2))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 3), makeCoordinate(3, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 2), makeCoordinate(3, 3))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(1, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testValidMoveGeneral() {
		// Move away 2 Advisors for testing on both side
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1)));
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5)));
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5)));
		
		// Move horizontally
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 2))); // RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 2))); // BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(1, 3))); // RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(1, 3))); // BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 4))); // RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 4))); // BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(1, 3))); // RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(1, 3))); // BLACK
	}
	
	@Test
	public void testInvalidMoveGeneral() {
		// RED turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to move not following the rule for General
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 2))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move not following the rule for General
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 2))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(3, 2))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(3, 2))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to move outside of specified zone (between 12 and 14)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(1, 3))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move outside of specified zone (between 12 and 14)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 2), makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testInvalidMoveNoPiece() {
		// RED turn - (2, 2) has no Piece
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 2), makeCoordinate(3, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testInvalidMoveWrongColor() {
		// RED turn - try to use BLACK piece
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(5, 1), makeCoordinate(3, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to use RED piece
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(5, 1), makeCoordinate(3, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testGetPieceAtPiece() {
		// Original location
		XiangqiPiece p = game.getPieceAt(makeCoordinate(1, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		
		// Move away from original position
		game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1));
		p = game.getPieceAt(makeCoordinate(1, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.NONE, p.getPieceType());
		assertEquals(XiangqiColor.NONE, p.getColor());
		
		// Get piece at new location
		p = game.getPieceAt(makeCoordinate(3, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		
		// Get piece at location of no piece
		p = game.getPieceAt(makeCoordinate(3, 3), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.NONE, p.getPieceType());
		assertEquals(XiangqiColor.NONE, p.getColor());
	}
	
	@Test
	public void testForWinGeneralTakenRedWin() {
		// RED turn - move Soldier up by one space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		// BLACK turn - move Soldier up by one space, take the RED's Soldier
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		// RED turn - move Chariot up by two space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
		// BLACK turn - move Chariot up by two space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
		// RED turn - move Chariot right by 2 space, take the BLACK's Soldier
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3)));
		// BLACK turn - move Chariot right by 1 space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 2)));
		// RED turn - move Chariot up by 2 space, take the BLACK's General and win the game
		assertEquals(MoveResult.RED_WINS, game.makeMove(makeCoordinate(3, 3), makeCoordinate(5, 3)));
	}
	
	@Test
	public void testForWinGeneralTakenBlackWin() {
		// RED turn - move Soldier up by one space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		// BLACK turn - move Soldier up by one space, take the RED's Soldier
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		// RED turn - move Chariot up by two space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
		// BLACK turn - move Chariot up by two space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
		// RED turn - move Chariot right by 2 space, take the BLACK's Soldier
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3)));
		// BLACK turn - move Chariot right by 2 space, take the RED's Chariot
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3)));
		// RED turn - move other Chariot up by 2 space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(3, 5)));
		// BLACK turn - move Chariot up by 2 space, take the RED's General and win the game
		assertEquals(MoveResult.BLACK_WINS, game.makeMove(makeCoordinate(3, 3), makeCoordinate(5, 3)));
	}
	
	@Test
	public void testForWinGeneralsFaceEachOtherBlackWin() {
		// RED turn - move Soldier up by one space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		// BLACK turn - move Soldier up by one space, take the RED's Soldier
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		// RED turn - move Chariot up by two space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
		// BLACK turn - move Chariot up by two space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
		// RED turn - move Chariot right by 2 space, take the BLACK's Soldier
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3)));
		// BLACK turn - move Chariot right by 1 space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 2)));
		// RED turn - move Chariot left by 2 space, make 2 Generals face each other and result in BLACK winning the game
		assertEquals(MoveResult.BLACK_WINS, game.makeMove(makeCoordinate(3, 3), makeCoordinate(3, 1)));
	}
	
	@Test
	public void testForWinGeneralsFaceEachOtherRedWin() {
		// RED turn - move Soldier up by one space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		// BLACK turn - move Soldier up by one space, take the RED's Soldier
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3)));
		// RED turn - move Chariot up by two space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
		// BLACK turn - move Chariot up by two space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
		// RED turn - move Chariot right by 2 space, take the BLACK's Soldier
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3)));
		// BLACK turn - move Chariot right by 2 space, take the RED's Chariot
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3)));
		// RED turn - move other Chariot up by 2 space
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(3, 5)));
		// BLACK turn - move Chariot left by 2 space, make 2 Generals face each other and result in RED winning the game
		assertEquals(MoveResult.RED_WINS, game.makeMove(makeCoordinate(3, 3), makeCoordinate(3, 1)));
	}
	
	@Test
	public void testForDraw() {
		// RED makes 10 moves and BLACK makes 9 moves that will not end the game
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // RED #1
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // BLACK #1
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3))); // RED #2
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(3, 3))); // BLACK #2
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3))); // RED #3
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(3, 3))); // BLACK #3
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(3, 5))); // RED #4
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(3, 5))); // BLACK #4
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 5), makeCoordinate(3, 3))); // RED #5
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 5), makeCoordinate(3, 3))); // BLACK #5
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1))); // RED #6
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 1))); // BLACK #6
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(3, 2))); // RED #7
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(3, 2))); // BLACK #7
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 2), makeCoordinate(2, 3))); // RED #8
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 2), makeCoordinate(2, 3))); // BLACK #8
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(1, 2))); // RED #9
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 3), makeCoordinate(1, 2))); // BLACK #9
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 3))); // RED #10
		
		// BLACK makes final 10th move, result the game as Draw
		assertEquals(MoveResult.DRAW, game.makeMove(makeCoordinate(1, 2), makeCoordinate(2, 3))); // BLACK #10
	}
}