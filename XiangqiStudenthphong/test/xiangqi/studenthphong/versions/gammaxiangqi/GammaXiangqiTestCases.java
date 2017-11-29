/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/

package xiangqi.studenthphong.versions.gammaxiangqi;

import static org.junit.Assert.*;
import org.junit.*;
import xiangqi.XiangqiGameFactory;
import xiangqi.common.*;
import static xiangqi.studenthphong.testutil.TestCoordinate.makeCoordinate;

/**
 * Test case for Gamma Xiangqi
 */

public class GammaXiangqiTestCases {
	
	private XiangqiGame game;
	
	// TODO Test Cases
	// 1. Test for factory
	// 2. Test for correct initial location of pieces (Red perspective)
	// 3. Test for correct initial location of pieces (Black perspective)
	// 4. Try to make valid move (Chariot)
	// 5. Try to make invalid move (Chariot)
	// 6. Try to make valid move (Elephant)
	// 7. Try to make invalid move (Elephant)
	// 8. Try to make valid move (Advisor)
	// 9. Try to make invalid move (Advisor)
	// 10. Try to make valid move (Soldier)
	// 11. Try to make invalid move (Soldier)
	// 12. Try to make valid move (General)
	// 13. Try to make invalid move (General)
	// 14. Try to make invalid move from square with no piece
	// 15. Try to make invalid move of using piece of wrong color
	// 16. Try to make invalid move using invalid coordinates
	// 17. Try to make invalid move when BLACK is currently being checked by RED
	// 18. Try to make invalid move when RED is currently being checked by BLACK
	// 19. Test for result decision (Generals are facing each other)
	// 20. Test for result decision (Draw)
	
	@Before
	public void setup() {
		game = XiangqiGameFactory.makeXiangqiGame(XiangqiGameVersion.GAMMA_XQ);
	}
	
	@Test
	public void factoryProducesGammaXiangqiGame() {
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
		p = game.getPieceAt(makeCoordinate(1, 9), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());	
		// ELEPHANT
		p = game.getPieceAt(makeCoordinate(1, 3), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ELEPHANT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(1, 7), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ELEPHANT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// ADVISOR
		p = game.getPieceAt(makeCoordinate(1, 4), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(1, 6), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// GENERAL
		p = game.getPieceAt(makeCoordinate(1, 5), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// SOLDIER
		p = game.getPieceAt(makeCoordinate(4, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(4, 3), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(4, 5), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(4, 7), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(4, 9), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		
		// Check for initial location of black pieces, in red perspective
		// CHARIOT
		p = game.getPieceAt(makeCoordinate(10, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(10, 9), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());	
		// ELEPHANT
		p = game.getPieceAt(makeCoordinate(10, 3), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ELEPHANT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(10, 7), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ELEPHANT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// ADVISOR
		p = game.getPieceAt(makeCoordinate(10, 4), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(10, 6), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// GENERAL
		p = game.getPieceAt(makeCoordinate(10, 5), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// SOLDIER
		p = game.getPieceAt(makeCoordinate(7, 1), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(7, 3), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(7, 5), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(7, 7), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(7, 9), XiangqiColor.RED);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
	}
	
	@Test
	public void testCorrectInitialLocationPiecesBlackPerspective() {
		XiangqiPiece p;
		
		// Check for initial location of red pieces, in red perspective
		// CHARIOT
		p = game.getPieceAt(makeCoordinate(1, 1), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(1, 9), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());	
		// ELEPHANT
		p = game.getPieceAt(makeCoordinate(1, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ELEPHANT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(1, 7), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ELEPHANT, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// ADVISOR
		p = game.getPieceAt(makeCoordinate(1, 4), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(1, 6), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// GENERAL
		p = game.getPieceAt(makeCoordinate(1, 5), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		// SOLDIER
		p = game.getPieceAt(makeCoordinate(4, 1), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(4, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(4, 5), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(4, 7), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		p = game.getPieceAt(makeCoordinate(4, 9), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.BLACK, p.getColor());
		
		// Check for initial location of black pieces, in red perspective
		// CHARIOT
		p = game.getPieceAt(makeCoordinate(10, 1), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(10, 9), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.CHARIOT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());	
		// ELEPHANT
		p = game.getPieceAt(makeCoordinate(10, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ELEPHANT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(10, 7), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ELEPHANT, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// ADVISOR
		p = game.getPieceAt(makeCoordinate(10, 4), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(10, 6), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.ADVISOR, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// GENERAL
		p = game.getPieceAt(makeCoordinate(10, 5), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.GENERAL, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		// SOLDIER
		p = game.getPieceAt(makeCoordinate(7, 1), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(7, 3), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(7, 5), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(7, 7), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
		p = game.getPieceAt(makeCoordinate(7, 9), XiangqiColor.BLACK);
		assertEquals(XiangqiPieceType.SOLDIER, p.getPieceType());
		assertEquals(XiangqiColor.RED, p.getColor());
	}

	@Test
	public void testMakeValidMoveChariot() {
		// Move up - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));
		// Move up - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1)));

		// Move down - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(2, 1)));
		// Move down - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(2, 1)));

		// Move right - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(2, 5)));
		// Move right - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(2, 5)));

		// Move left - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(2, 4)));
		// Move left - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(2, 4)));

		// Take another piece - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 4), makeCoordinate(10, 4)));
	}
	
	@Test
	public void testMakeInvalidMoveChariot() {
		// RED turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// RED turn - try to move to destination that has obstacle in between (horizontal)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(7, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// RED turn - same source and destination coordination (no movement)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// RED turn - try to move not following the rule for Chariot
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);

		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);

		// BLACK turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// BLACK turn - try to move to destination that has obstacle in between (horizontal)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(6, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// BLACK turn - same source and destination coordination (no movement)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// BLACK turn - try to move not following the rule for Chariot
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);

		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(2, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
	}
	
	@Test
	public void testMakeValidMoveElephant() {
		// Move up right - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(3, 5)));
		// Move up right - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(3, 5)));
		
		// Move up left - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 5), makeCoordinate(5, 3)));
		// Move up left - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 5), makeCoordinate(5, 3)));
		
		// Move down left - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 3), makeCoordinate(3, 1)));
		// Move down left - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 3), makeCoordinate(3, 1)));
		
		// Move down right - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(1, 3)));
		// Move down right - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(1, 3)));
		
		// Set up for Elephant to take a piece
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5, 3))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5, 3))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 3), makeCoordinate(6, 3))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 3), makeCoordinate(6, 3))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 7), makeCoordinate(3, 9))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 7), makeCoordinate(3, 9))); // BLACK turn
		
		// Take another piece - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 9), makeCoordinate(5, 7)));
		// Take another piece - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 9), makeCoordinate(5, 7)));
	}
	
	@Test
	public void testMakeInvalidMoveElephant() {
		// RED turn - try to move not following the rule of Elephant
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
				
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move not following the rule of Elephant
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 2)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
				
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(3, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(3, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// RED turn - same source and destination coordination (no movement)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// RED turn - try to move not following the rule for Elephant
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(2, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);

		// BLACK turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(3, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// BLACK turn - same source and destination coordination (no movement)
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(1, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		// BLACK turn - try to move not following the rule for Elephant
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 1), makeCoordinate(2, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(2, 2))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(2, 2))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to move to destination that has obstacle in between
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(3, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(3, 5))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 3), makeCoordinate(3, 5))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 5), makeCoordinate(5, 7))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move to destination that has obstacle in between
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 3), makeCoordinate(3, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 5), makeCoordinate(5, 7))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to move across the river
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(5, 7), makeCoordinate(7, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 7), makeCoordinate(3, 5))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move across the river
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(5, 7), makeCoordinate(7, 5)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testMakeValidMoveAdvisor() {
		// Move diag. up and right - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5)));
		// Move diag. up and right - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5)));
		
		// Move diag. up and left - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(3, 4)));
		// Move diag. up and left - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(3, 4)));
		
		// Move diag. down and right - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 4), makeCoordinate(2, 5)));
		// Move diag. down and right - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 4), makeCoordinate(2, 5)));
		
		// Move diag. down and left - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(1, 4)));
		// Move diag. down and left - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(1, 4)));
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 5), makeCoordinate(5, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 5), makeCoordinate(5, 5))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 5), makeCoordinate(6, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(6, 5), makeCoordinate(7, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(2, 2))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(7, 5), makeCoordinate(8, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 2), makeCoordinate(2, 3))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(8, 5), makeCoordinate(9, 5))); // RED turn
		
		// Take another piece - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5)));
	}
	
	@Test
	public void testMakeInvalidMoveAdvisor() {
		// RED turn - try to move not following the rule for Advisor
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 4), makeCoordinate(3, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move not following the rule for Advisor
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 4), makeCoordinate(3, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 5), makeCoordinate(1, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(3, 4))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 5), makeCoordinate(1, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(3, 4))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to move out of the palace
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(3, 4), makeCoordinate(4, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(3, 4), makeCoordinate(2, 5))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move out of the palace
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(3, 4), makeCoordinate(4, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testMakeValidMoveSoldier() {
		// Move up - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 1), makeCoordinate(5, 1)));
		
		// Move up - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 9), makeCoordinate(5, 9)));
		
		// Move horizontally along the river (to the right) - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 1), makeCoordinate(5, 2)));
		
		// Move horizontally along the river (to the left) - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 9), makeCoordinate(5, 8)));
		
		// Move horizontally along the river (to the left) - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 2), makeCoordinate(5, 1)));
				
		// Move horizontally along the river (to the right) - BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 8), makeCoordinate(5, 9)));
		
		// Move up and take another piece - RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 1), makeCoordinate(6, 1)));
	}
	
	@Test
	public void testMakeInvalidMoveSoldier() {
		// RED turn - try to move not following the rule for Soldier
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(4, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(3, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5, 3))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to move not following the rule for Soldier
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(4, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(3, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 3), makeCoordinate(5, 3))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 1), makeCoordinate(5, 1))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 1), makeCoordinate(5, 1))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 1), makeCoordinate(5, 2))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 1), makeCoordinate(5, 2))); // BLACK turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// RED turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(5, 2), makeCoordinate(5, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 3), makeCoordinate(6, 3))); // RED turn - valid move
		assertTrue(game.getMoveMessage().length() == 0);
		
		// BLACK turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(5, 2), makeCoordinate(5, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testMakeValidMoveGeneral() {
		// Move away Advisor for testing on both side
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 5))); // BLACK turn
		
		// Move horizontally
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(1, 4))); // RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(1, 4))); // BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 4))); // RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(2, 4))); // BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 4), makeCoordinate(1, 4))); // RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 4), makeCoordinate(1, 4))); // BLACK
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(1, 5))); // RED
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 4), makeCoordinate(1, 5))); // BLACK
	}
	
	@Test
	public void testMakeInvalidMoveGeneral() {
		// RED turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 5), makeCoordinate(1, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 5), makeCoordinate(1, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(2, 5))); // RED turn - valid move
		
		// BLACK turn - try to take piece of same color
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 5), makeCoordinate(1, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 5), makeCoordinate(1, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 5), makeCoordinate(2, 5))); // BLACK turn - valid move
		
		// RED turn - try to move not following the rule of General
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 5), makeCoordinate(3, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 5), makeCoordinate(3, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(2, 4))); // RED turn - valid move
		
		// BLACK turn - try to move not following the rule of General
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 5), makeCoordinate(3, 4)));
		assertTrue(game.getMoveMessage().length() >= 1);
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 5), makeCoordinate(3, 6)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(2, 4))); // BLACK turn - valid move
		
		// RED turn - try to move out of the palace
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 4), makeCoordinate(2, 3)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 4), makeCoordinate(2, 5))); // RED turn - valid move
		
		// BLACK turn - try to move out of the palace
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(2, 4), makeCoordinate(2, 3)));
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
		// RED turn - (10, 1) belongs to BLACK
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(10, 1), makeCoordinate(9, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testInvalidMakeMove() {
		// RED turn - makeMove with source Coordinate out of bound
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(0, 0), makeCoordinate(3, 1)));
		assertTrue(game.getMoveMessage().length() >= 1);
		
		// RED turn - makeMove with destination Coordinate out of bound
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(1, 0)));
		assertTrue(game.getMoveMessage().length() >= 1);
	}
	
	@Test
	public void testForIllegalGeneralIsCheckedByRed() {
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 5), makeCoordinate(5, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 5), makeCoordinate(5, 5))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 5), makeCoordinate(6, 5))); // RED turn - BLACK soldier is taken
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 1), makeCoordinate(5, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(6, 5), makeCoordinate(7, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 1), makeCoordinate(6, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(7, 5), makeCoordinate(8, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(6, 1), makeCoordinate(7, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(8, 5), makeCoordinate(9, 5))); // RED turn
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(7, 1), makeCoordinate(8, 1))); // BLACK turn
	}
	
	@Test
	public void testForIllegalGeneralIsCheckedByBlack() {
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 5), makeCoordinate(5, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 5), makeCoordinate(5, 5))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 5), makeCoordinate(6, 5))); // RED turn - BLACK soldier is taken
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(6, 5), makeCoordinate(7, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(2, 5))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(7, 5), makeCoordinate(8, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 5), makeCoordinate(3, 5))); // BLACK turn - RED soldier is taken
		assertEquals(MoveResult.ILLEGAL, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
	}
	
	@Test
	public void testForWinGeneralFacesEachOther() {
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 5), makeCoordinate(5, 5))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 5), makeCoordinate(5, 5))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(5, 5), makeCoordinate(6, 5))); // RED turn - BLACK soldier is taken
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(4, 1), makeCoordinate(5, 1))); // BLACK turn
		assertEquals(MoveResult.BLACK_WINS, game.makeMove(makeCoordinate(6, 5), makeCoordinate(6, 4))); // RED turn - BLACK WIN
	}
	
	@Test
	public void testForDraw() {
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // RED turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(2, 1), makeCoordinate(1, 1))); // BLACK turn
		assertEquals(MoveResult.OK, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // RED turn
		assertEquals(MoveResult.DRAW, game.makeMove(makeCoordinate(1, 1), makeCoordinate(2, 1))); // BLACK turn - 25th turn of BLACK - DRAW
	}
}