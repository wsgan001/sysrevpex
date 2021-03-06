/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.view.color;

import java.awt.Color;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class PseudoRainbowScale extends ColorScale
{

	private static final Color[] colors = new Color[256];
	static {
        float dx = 0.8f;
        for (int i = 0; i < colors.length; i++) {
            float aux = (float) i / (colors.length - 1);
            float scaled = (6 - 2 * dx) * aux + dx;

            float r = Math.max(0, (3 - Math.abs(scaled - 4) - Math.abs(scaled - 5)) / 2);
            float g = Math.max(0, (4 - Math.abs(scaled - 2) - Math.abs(scaled - 4)) / 2);
            float b = Math.max(0, (3 - Math.abs(scaled - 1) - Math.abs(scaled - 2)) / 2);

            colors[i] = new Color(r, g, b);
        }
    }
    
	@Override
	public int getNumberColors()
	{
		return PseudoRainbowScale.colors.length;
	}

	@Override
	public Color getIndexedColor(int i)
	{
		return PseudoRainbowScale.colors[i];
	} 
}
