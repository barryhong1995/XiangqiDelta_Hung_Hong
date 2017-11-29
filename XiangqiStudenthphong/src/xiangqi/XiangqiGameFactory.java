/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/

package xiangqi;

import xiangqi.common.*;
import xiangqi.studenthphong.versions.alphaxiangqi.AlphaXiangqiGame;
import xiangqi.studenthphong.versions.betaxiangqi.BetaXiangqiGame;
import xiangqi.studenthphong.versions.deltaxiangqi.DeltaXiangqiGame;
import xiangqi.studenthphong.versions.gammaxiangqi.GammaXiangqiGame;

/**
 * A simple factory object that creates the appropriate instance of a XiangqiGame.
 * @version Dec 26, 2016
 */
public class XiangqiGameFactory
{
	/**
	 * Factory method that returns an instance of the requested game.
	 * @param version the version requested
	 * @return the instance of the requested game
	 */
	public static XiangqiGame makeXiangqiGame(XiangqiGameVersion version)
	{
		if (version == XiangqiGameVersion.ALPHA_XQ) {
			return new AlphaXiangqiGame();
		}
		else if (version == XiangqiGameVersion.BETA_XQ) {
			return new BetaXiangqiGame();
		} 
		else if (version == XiangqiGameVersion.GAMMA_XQ) {
			return new GammaXiangqiGame();
		} 
		else if (version == XiangqiGameVersion.DELTA_XQ) {
			return new DeltaXiangqiGame();
		} 
		else return null;
	}
}
