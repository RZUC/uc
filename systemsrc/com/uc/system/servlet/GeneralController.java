/**
 * ***************************************************
 * Copyright (C), NingBo ZhiWeiReach info. Co., Ltd. *
 *****************************************************
 * 类的详细说明 
 * 
 * @author 东临碣石
 * @Date 2016年1月22日
 * @version 1.00 
 */
package com.uc.system.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @ClassName: GeneralController
 * @author 落花流水
 * @date 2016年1月22日 上午11:40:32
 */
public abstract class GeneralController
{
    final static String REQUESTStrOK = "OK";
    
    final static String REQUESTStrFAIL = "FAIL";
    
    protected Logger log = LoggerFactory.getLogger(GeneralController.class);
    
    void getJsonStrByString(String json, HttpServletResponse arg1)
    {
        arg1.setContentType("text/html;charset=utf-8");
        PrintWriter p;
        try
        {
            p = arg1.getWriter();
            p.write(json);
            p.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    void getBooleanJSon(boolean state, String message, HttpServletResponse arg1)
    {
        JSONObject resultJson = new JSONObject();
        resultJson.put("message", message);
        resultJson.put("state", state);
        getJsonStrByString(resultJson.toString(), arg1);
    }
    
    void getJsonStrByObject(Object obj, HttpServletResponse arg1)
    {
        if (obj != null)
        {
            getJsonStrByString(JSONObject.fromObject(obj).toString(), arg1);
        }
        else
        {
            getJsonStrByString("", arg1);
        }
    }
    
    void getJsonStrByList(List list, HttpServletResponse arg1)
    {
        if (list != null)
        {
            getJsonStrByString(JSONArray.fromObject(list).toString(), arg1);
        }
        else
        {
            getJsonStrByString("", arg1);
        }
    }
    
    /**
     * @Title: getJsonStrDataByList
     * @Description: 返回Data数据
     * @param @param list
     * @param @param arg1 设定文件
     * @return void 返回类型
     */
    void getJsonStrDataByList(List list, HttpServletResponse response)
    {
        Map<String, List> map = new HashMap<String, List>();
        map.put("data", list);
        if (list != null)
        {
            getJsonStrByString(JSONArray.fromObject(map).toString(), response);
        }
        else
        {
            getJsonStrByString("", response);
        }
    }
    
    private byte[] readFile(File file)
    {
        byte[] b = new byte[(int)file.length()];
        try
        {
            InputStream in = new FileInputStream(file);
            in.read(b);
            in.close();
        }
        catch (FileNotFoundException e)
        {
            log.error("文件未找到：{}", e.getMessage());
        }
        catch (IOException e)
        {
            log.error("读取文件到byte数组出错：{}", e.getMessage());
        }
        return b;
    }
    
    public void downLoad(File file, HttpServletResponse response)
    {
        // log.info("filename\t{}", file.getName());
        // response.setContentType("application/zip");
        // long fileLength = file.length();
        // log.info("fileSize:\t{}", fileLength);
        // response.setContentLength(Integer.valueOf(String.valueOf(fileLength)));
        // response.addHeader("Content-Disposition",
        // "attachment; filename=\"" + new
        // String(file.getName().getBytes("utf-8")) + "\"");
        // byte[] b = readFile(file);
        // log.info("b Size:\t{}", fileLength);
        // try
        // {
        // log.info("os is null:\t{}", os == null);
        // os.write(b);
        // log.info("write:\t{}", fileLength);
        // }
        // catch (Exception e)
        // {
        // System.out.println(" write byte[] error");
        // e.printStackTrace();
        // }
        // os.flush();
        // log.info("flush exception!!!");
        // os.close();
        // log.info("close exception!!!");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        
        try
        {
            long fileLength = file.length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition",
                "attachment; filename=" + new String(file.getName().getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
            {
                bos.write(buff, 0, bytesRead);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bis != null)
                try
                {
                    bis.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            if (bos != null)
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
        }
    }
}
