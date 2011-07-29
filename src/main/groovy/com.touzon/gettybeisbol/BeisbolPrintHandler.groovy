package com.touzon.gettybeisbol

import org.jboss.netty.channel.SimpleChannelHandler
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.MessageEvent

/**
 * User: carlos
 * Date: 7/27/11
 * Time: 10:13 AM
 */
class BeisbolPrintHandler extends SimpleChannelHandler {
  @Override
  void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    print e.message
  }


}
