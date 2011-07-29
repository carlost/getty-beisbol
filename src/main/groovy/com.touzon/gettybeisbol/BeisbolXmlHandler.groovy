package com.touzon.gettybeisbol

import org.jboss.netty.channel.SimpleChannelHandler
import org.jboss.netty.channel.ChannelHandlerContext
import org.jboss.netty.channel.MessageEvent
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document
import org.jboss.netty.buffer.ChannelBuffer

/**
 * User: carlos
 * Date: 7/26/11
 * Time: 9:40 PM
 */
class BeisbolXmlHandler extends SimpleChannelHandler {
  
  @Override
  void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    ChannelBuffer m = e.message

    def bytes = new byte[m.readableBytes()]
    m.readBytes(bytes)

    Document doc =  DocumentBuilderFactory.
        newInstance().
        newDocumentBuilder().
        parse(new ByteArrayInputStream( bytes ))
  }


}
