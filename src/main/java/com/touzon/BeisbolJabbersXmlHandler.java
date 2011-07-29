package com.touzon;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

/**
 * User: carlos
 * Date: 7/29/11
 * Time: 1:56 PM
 */
public class BeisbolJabbersXmlHandler extends SimpleChannelHandler {

  @Override
  public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    ChannelBuffer m = (ChannelBuffer) e.getMessage();

    byte[] bytes = new byte[m.readableBytes()];
    m.readBytes(bytes);

      try {
          Document doc =  DocumentBuilderFactory.
              newInstance().
              newDocumentBuilder().
              parse(new ByteArrayInputStream(bytes));
      } catch (Exception exc) {
          throw new RuntimeException(exc);
      }
  }
}
