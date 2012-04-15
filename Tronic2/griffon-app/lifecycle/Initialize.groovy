/*
 * This script is executed inside the UI thread, so be sure to  call 
 * long running code in another thread.
 *
 * You have the following options
 * - execOutsideUI { // your code }
 * - execFuture { // your code }
 * - Thread.start { // your code }
 *
 * You have the following options to run code again inside the UI thread
 * - execInsideUIAsync { // your code }
 * - execInsideUISync { // your code }
 */

import groovy.swing.SwingBuilder

import java.awt.Color
import javax.swing.UIManager

import static griffon.util.GriffonApplicationUtils.isMacOSX

SwingBuilder.lookAndFeel((isMacOSX ? 'system' : 'nimbus'), 'gtk', ['metal', [boldFonts: false]])
defaults = UIManager.getLookAndFeelDefaults()
defaults.put("Panel.background", new Color(133, 45, 0))
defaults.put("OptionPane.background", new Color(133, 45, 0))