package kr.or.ddit.batch.yogurt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import ch.qos.logback.classic.Logger;
import kr.or.ddit.batch.yogurt.model.CycleVo;
import kr.or.ddit.batch.yogurt.model.DailyVo;

public class YogurtProcessor implements ItemProcessor<CycleVo, List<DailyVo>> {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(YogurtProcessor.class);
	
	// jobLauncher를 실행하면서 두번째 인자로 넣어준 JobParameter 값을 SPEL을 통해 주입
	// 단 jobParameters에 접근하기 위해서는 해당 스프링 빈의 scope를 step으로 지정해야한다
	@Value("#{jobParameters[ym]}")
	private String ym ;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	 
	@Override
	public List<DailyVo> process(CycleVo item) throws Exception {
		// 생성 월이 2020 11월
		// cid = 1, pid = 100, day = 2, cnt = 3
		// ==>
		// cid = 1, pid = 100, dt = 20201102, cnt = 3
		// cid = 1, pid = 100, dt = 20201109, cnt = 3
		// cid = 1, pid = 100, dt = 20201116, cnt = 3
		// cid = 1, pid = 100, dt = 20201123, cnt = 3
		// cid = 1, pid = 100, dt = 20201130, cnt = 3
		
		logger.debug("ym : {}, item : {} " , ym, item);
		List<DailyVo> dailyVoList = new ArrayList<DailyVo>();
		
		//calendar는 싱글톤 방식으로 생성 
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(ym.substring(0,4))); // 시작은 포함 끝은 포함하지않음
		calendar.set(Calendar.MONTH, Integer.parseInt(ym.substring(4))-1); // 월 시작이 0이므로 1빼야함
		calendar.set(calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH) ); // 30 해당캘린더 월의 마지막일을 설정
		
		// calendar 2020 11 30
		Date endTime = calendar.getTime();
		String endTimeStr = sdf.format(endTime);	
		int endTimeInt = Integer.parseInt(endTimeStr);
		
		// 해당 월의 1일로 설정
		calendar.set(calendar.DAY_OF_MONTH,1);
		Date dt = calendar.getTime();
		String dtStr = sdf.format(dt);
		int dtInt = Integer.parseInt(dtStr);
		// calendar 2020 11 01
		
		//------------여기까지 달력---------------
		
		while(endTimeInt >= dtInt) {
//		while(endTime.after(calendar.getTime())) {
			// calendar 날짜가 item의 애음요일과 같을 때만 dailyVo를 생성
			if(item.getDay() == calendar.get(Calendar.DAY_OF_WEEK)) {// 주간요일
				DailyVo dailyVo = new DailyVo();
				dailyVo.setCid(item.getCid());
				dailyVo.setPid(item.getPid());
				dailyVo.setDt(new SimpleDateFormat("yyyyMMdd").format(calendar.getTime()));
				dailyVo.setCnt(item.getCnt());
				
				dailyVoList.add(dailyVo);
			}
			// 다음날 짜로 설정
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);  
			dt = calendar.getTime();
			dtStr = sdf.format(dt);
			dtInt = Integer.parseInt(dtStr);
		}
		
		return dailyVoList;
	}

}
