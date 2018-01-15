package com.pikapika.app.mb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pikapika.app.entity.BbsEntity;
import com.pikapika.app.mapper.BbsMapper;
import com.pikapika.app.mb.service.MbBbsService;
import com.pikapika.app.service.RedisService;
import com.pikapika.app.util.CodeUtil;
@Service
public class MbBbsServiceImpl implements MbBbsService {
	
	private BbsMapper bbsMapper;
	
    private JavaMailSender mailSender; //自动注入的Bean
    
    private StringRedisTemplate stringRedisTemplate;
    
    private RedisService redisService;
    
    private String sender;
	
    public void setSender(String sender){
    	this.sender = sender;
    }
	
	
	public MbBbsServiceImpl(BbsMapper bbsMapper,JavaMailSender mailSender, 
			StringRedisTemplate stringRedisTemplate, RedisService redisService) {
		this.bbsMapper = bbsMapper;
		this.mailSender = mailSender;
		this.stringRedisTemplate = stringRedisTemplate;
		this.redisService = redisService;
	}

	@Override
	public String searchAllBbs(String pageNum, String pageSize) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		stringRedisTemplate.opsForValue().get("mykey");
		redisService.get("mykey");
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		if(pageNum == null || pageSize == null 
    			|| pageNum.isEmpty() || pageSize.isEmpty()){
			resultMap.put("state", "fail");
			resultMap.put("msg", "页码或显示页数不能为空");
			result = mapper.writeValueAsString(resultMap);
			return result;
		}
    	if(!CodeUtil.isNub(pageNum)
    			|| !CodeUtil.isNub(pageSize)){
    		resultMap.put("state", "fail");
    		resultMap.put("msg", "页码或显示页数参数必须是数字");
    		result = mapper.writeValueAsString(resultMap);
			return result;
    	}
    	PageHelper.startPage(Integer.valueOf(pageNum), Integer.valueOf(pageSize));
		List<BbsEntity> bbsList = bbsMapper.getAll();
		if(bbsList!= null
			&& !bbsList.isEmpty()){
			PageInfo<BbsEntity> pageInfo = new PageInfo<>(bbsList);
			System.out.println(pageInfo.getTotal());
				result = mapper.writeValueAsString(bbsList);
		}
		return result;
	}

	@Override
	public String savePost(List<Attachment> files) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String sendMail(String toEmail) throws Exception {
		Map<String, String> resultMap = new HashMap<>();
		String result = "";
		ObjectMapper mapper = new ObjectMapper();
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(toEmail); //自己给自己发送邮件
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        try {
			mailSender.send(message);
			resultMap.put("state", "success");
			resultMap.put("msg", "发送成功");
		} catch (MailException e) {
			resultMap.put("state", "fail");
			resultMap.put("msg", "发送失败");
		}
        result = mapper.writeValueAsString(resultMap);
		return result;
	}

}
