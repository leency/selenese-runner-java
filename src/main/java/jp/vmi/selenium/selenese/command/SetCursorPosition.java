// This code is based on:
//
//   com.thoughtworks.selenium.webdriven.commands.SetCursorPosition
//
// in Selenium WebDriver.
//
// The following copyright is copied from original.
// ---
// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package jp.vmi.selenium.selenese.command;

import org.openqa.selenium.WebElement;

import jp.vmi.selenium.selenese.Context;
import jp.vmi.selenium.selenese.result.Error;
import jp.vmi.selenium.selenese.result.Result;

import static jp.vmi.selenium.selenese.command.ArgumentType.*;
import static jp.vmi.selenium.selenese.result.Success.*;

/**
 * Re-implementation of SetCursorPosition.
 */
public class SetCursorPosition extends AbstractCommand {

    private static final int ARG_LOCATOR = 0;
    private static final int ARG_POSITION = 0;

    SetCursorPosition(int index, String name, String... args) {
        super(index, name, args, LOCATOR, VALUE);
    }

    @Override
    protected Result executeImpl(Context context, String... curArgs) {
        WebElement element = context.findElement(curArgs[ARG_LOCATOR]);
        int position;
        try {
            position = Integer.parseInt(curArgs[ARG_POSITION]);
        } catch (NumberFormatException e) {
            return new Error("Position is not a number: " + curArgs[ARG_POSITION]);
        }
        context.getJSLibrary().setCursorPosition(context.getWrappedDriver(), element, position);
        return SUCCESS;
    }
}
