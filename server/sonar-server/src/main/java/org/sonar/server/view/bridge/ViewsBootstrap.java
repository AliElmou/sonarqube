/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.view.bridge;

import org.picocontainer.Startable;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.api.utils.log.Profiler;
import org.sonar.core.platform.ComponentContainer;
import org.sonar.server.views.ViewsBridge;

/**
 * Startup task to responsible to bootstrap the Views plugin when it is installed.
 */
public class ViewsBootstrap implements Startable {
  private static final Logger LOGGER = Loggers.get(ViewsBootstrap.class);

  private final ComponentContainer componentContainer;

  public ViewsBootstrap(ComponentContainer componentContainer) {
    this.componentContainer = componentContainer;
  }

  @Override
  public void start() {
    ViewsBridge viewsBridge = componentContainer.getComponentByType(ViewsBridge.class);
    if (viewsBridge != null) {
      Profiler profiler = Profiler.create(LOGGER).startInfo("Bootstrapping views");
      viewsBridge.startViews(componentContainer);
      profiler.stopInfo();
    }
  }

  @Override
  public void stop() {
    // this class is stopped right after it has been started as an element of PlatformLevelStartup
    // stopping Views is handled by ViewsStopper
  }
}
