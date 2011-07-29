package com.touzon.gettybeisbol

import java.util.concurrent.Executors
import org.jboss.netty.bootstrap.ClientBootstrap
import org.jboss.netty.channel.ChannelPipelineFactory
import org.jboss.netty.channel.Channels
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory
import org.jboss.netty.handler.codec.string.StringDecoder
import org.jboss.netty.util.CharsetUtil

import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder
import org.jboss.netty.buffer.ChannelBuffers
import org.jboss.netty.buffer.ChannelBuffer
import com.touzon.BeisbolJabbersXmlHandler

class BerryBerryGoodToMeNetworkClient {

  static final def HOST = 'ec2-50-19-181-80.compute-1.amazonaws.com'
  static final def PORT = 8124
  static final def DELIMITER = '</MLB-event>'
  static final def CHARSET = CharsetUtil.UTF_8

  static final void main(args) {
    def startTime = System.nanoTime()
    // Configure the client.
    ClientBootstrap bootstrap = new ClientBootstrap(
        new NioClientSocketChannelFactory(
            Executors.newCachedThreadPool(),
            Executors.newCachedThreadPool()))

    //create pipeline factory
    def pipeLineFactory = {
      def pipeline = Channels.pipeline()
      //know how to segment the messages
      pipeline.addLast("delimiter", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,false,[ChannelBuffers.wrappedBuffer(DELIMITER.getBytes(CHARSET))] as ChannelBuffer[]))
      //convert to strings
      //pipeline.addLast("string decoder", new StringDecoder(CHARSET))
      //handle the message
      pipeline.addLast("handler", new BeisbolXmlHandler())
      return pipeline
    } as ChannelPipelineFactory

    bootstrap.pipelineFactory = pipeLineFactory

    def future = bootstrap.connect(new InetSocketAddress(HOST, PORT))

    future.channel.closeFuture.awaitUninterruptibly()

    bootstrap.releaseExternalResources()

    def elapsedTime = System.nanoTime() - startTime

    println "#" * 50
    println "elapsed time: ${elapsedTime}"
    println "this took ${(double)elapsedTime / 1000000000.0} seconds to run"
    println "#" * 50

  }

}