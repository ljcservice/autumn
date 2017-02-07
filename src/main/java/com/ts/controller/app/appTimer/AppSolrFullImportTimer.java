package com.ts.controller.app.appTimer;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.ts.controller.app.SearchAPI.BusinessHandler;
import com.ts.controller.app.SearchAPI.BusinessAPI.util.ReadPropertiesFiles;
import com.ts.controller.app.SearchAPI.BusinessAPI.util.SolrRecordHandler;

/**
 * @描述：solr 服务定时全量更新更新方法
 * @作者：SZ
 * @时间：2016年11月23日 上午8:48:22
 */
@Component
public class AppSolrFullImportTimer implements Job {
	/**
	 * @描述：定时全量更新solr索引数据
	 * @作者：SZ
	 * @时间：2016年11月23日 上午8:50:31
	 */
	public void updateSolrData() {
		Map<String, String> map = BusinessHandler.typeMap;
		
		for (String key : map.keySet()) {
			String urlstr = ReadPropertiesFiles.getSolrUrl(map.get(key));
			if(SolrRecordHandler.isIdle(urlstr)){
				String url = urlstr + "/dataimport?command=full-import";
				SolrRecordHandler.fullImport(url);
				SolrRecordHandler.isIdle(urlstr);
			}
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		updateSolrData();
	}
}
