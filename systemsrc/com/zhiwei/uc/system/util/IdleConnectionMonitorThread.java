/**
 * 
 */
package com.zhiwei.uc.system.util;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/** 
 * @Description: 该线程通过连接管理器清空失效连接和过长连接
 * @author Tou Tang
 * @date 2014-11-14 下午5:07:35  
 */
public class IdleConnectionMonitorThread extends Thread {
	private final static long time = 20000l;
    private final PoolingHttpClientConnectionManager clientManager;
    private volatile boolean shutdown;
    public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager clientManager) {
      super();
      this.clientManager = clientManager;
    }
    
    @Override
    public void run() {
      try {
        while (!shutdown) {
          synchronized (this) {
            wait(time);
            // 关闭失效连接
            clientManager.closeExpiredConnections();
//            //关闭空闲超过30秒的连接
//            clientManager.closeIdleConnections(30, TimeUnit.SECONDS);
          }
        }
      } catch (InterruptedException ex) {
    	  ex.printStackTrace();
      }
    }
    
    /**
     * 
    * @Description: 停止该线程
    * @param 
    * @return void
     */
    public void shutdown() {
      shutdown = true;
      synchronized (this)
      {
        notifyAll();
      }
    }

}
