/*
 * This file is part of Spoutcraft (http://www.spout.org/).
 *
 * Spoutcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoutcraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spoutcraft.client.gui.settings;

import java.util.UUID;

import org.spoutcraft.client.config.ConfigReader;
import org.spoutcraft.spoutcraftapi.event.screen.ButtonClickEvent;

public class FancyWeatherButton extends AutomatedCheckBox {
	UUID fancyGraphics;
	public FancyWeatherButton(UUID fancyGraphics) {
		super("Fancy Weather");
		this.fancyGraphics = fancyGraphics;
		setChecked(ConfigReader.fancyWeather);
		setTooltip("Fancy Weather\nDefault - as set by setting Graphics\nFast  - light rain/snow, faster\nFancy - heavy rain/snow, slower\nOFF - no rain/snow, fastest\nWhen rain is OFF the splashes and rain sounds\nare still active.");
	}

	@Override
	public void onButtonClick(ButtonClickEvent event) {
		ConfigReader.fancyWeather = !ConfigReader.fancyWeather;
		ConfigReader.write();
		((FancyGraphicsButton)getScreen().getWidget(fancyGraphics)).custom = true;
	}
}
