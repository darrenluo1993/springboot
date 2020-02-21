package pers.darren.springboot.config;

import static com.fr.cert.token.SignatureAlgorithm.HS256;
import static java.util.Calendar.MINUTE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fr.cert.token.Jwts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * FineReport报表配置
 * <p>
 * 注意：地址配置必须指定到模板，其它可自由配置的业务参数，由具体的业务代码
 * <p>
 *
 * @author liush
 * @data 2018年3月18日
 * @filename WebReportConfig.java
 */
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "business.report")
public class WebReportConfig {
	/**
	 * 用于生成数字签名的密钥
	 */
	private String fineSignatureKey;
	/**
	 * 数字签名有效时长(分钟)，默认为10分钟
	 */
	private int signatureDuration = 10;

	/**
	 * FineReport报表链接追加数字签名
	 *
	 * @创建人 Bao Mai
	 * @创建时间 2020年1月16日 下午4:04:53
	 * @param url
	 * @return
	 */
	private String appendSignature(final String url) {
		final String signature = this.generateSignature(obtainReportName(url));
		return new StringBuilder(url).append("&fine_digital_signature=").append(signature).toString();
	}

	/**
	 * 根据URL获取FineReport报表文件名
	 *
	 * @创建人 Bao Mai
	 * @创建时间 2020年1月16日 下午4:00:51
	 * @param url
	 * @return
	 */
	private static String obtainReportName(final String url) {
		// 根据?分割URL的链接与参数
		final String[] urlPartArr = url.split("\\?");
		if (urlPartArr.length > 1) {
			// 根据&分割URL的参数
			final String[] urlParamArr = urlPartArr[1].split("&");
			// 遍历URL参数数组
			for (final String urlParam : urlParamArr) {
				// 若参数名是viewlet或者reportlet，获取参数值
				if (urlParam.startsWith("viewlet=") || urlParam.startsWith("reportlet=")) {
					return urlParam.split("=")[1];
				}
			}
		}
		return EMPTY;
	}

	/**
	 * 生成FineReport数字签名
	 *
	 * @创建人 Bao Mai
	 * @创建时间 2020年1月16日 下午4:05:33
	 * @param reportName
	 * @return
	 */
	private String generateSignature(final String reportName) {
		// 获取日历实例
		final Calendar calendar = Calendar.getInstance();
		// 获取日历的当前时间
		final Date currTime = calendar.getTime();
		// 通过日历将当前时间加上指定时长（数字签名有效时长(分钟)）
		calendar.add(MINUTE, this.signatureDuration);
		// 获取数字签名失效时间
		final Date expireTime = calendar.getTime();
		// 生成数字签名并返回
		return Jwts.builder().setIssuer(EMPTY).setId(EMPTY).setSubject(reportName)
				.signWith(HS256, this.fineSignatureKey).setIssuedAt(currTime).setExpiration(expireTime).compact();
	}

	/**
	 * 报表服务地址
	 */
	@Getter
	private String reportServer;
	/**
	 * 工程计划量导出报表访问地址配置
	 */
	private String PlanningExportUri;
	/**
	 * 工程实际量导出报表访问地址配置
	 */
	private String ActualingExportUri;
	/**
	 * 批次导出报表访问地址配置
	 */
	private String BatchExportUri;
	/**
	 * 单项导出报表访问地址配置
	 */
	private String ProjectExportUri;
	/**
	 * 里程碑计划执行报表
	 */
	private String MilestonePlanExcuteUri;
	/**
	 * 里程碑实际完成报表
	 */
	private String MilestoneAccuExcuteUri;
	/**
	 * 里程碑平台和APP实际完成报表
	 */
	private String MilestoneAppAccuExcuteUri;
	/**
	 * 光伏工程导出报表下载地址
	 */
	private String LightExportUri;
	/**
	 * 光伏工程汇总报表地址
	 */
	private String LightPhoProjectSumUri;
	/**
	 * 光伏工程明细报表地址
	 */
	private String LightPhoProjectUri;
	/**
	 * 里程碑批次节点计划编制导出报表地址
	 */
	private String MilestoneBatchNodeExportUri;
	/**
	 * 里程碑批次节点计划审核导出报表地址
	 */
	private String MilestoneBatchAuditExportUri;
	/**
	 * 里程碑单项节点计划编制导出报表地址
	 */
	private String MilestoneProjectNodeExportUri;
	/**
	 * 物资里程碑节点计划填报导出报表地址
	 */
	private String MilestoneMaterialNodeExportUri;
	/**
	 * 工程完成情况 之 已竣工报表
	 */
	private String ProjectCompleteReportUrl;
	/**
	 * 工程完成情况 之 已未竣工报表
	 */
	private String ProjectUnCompleteReportUrl;
	/**
	 * 进程碑月结报表
	 */
	private String milestoneMonthPlanUrl;
	/**
	 * 进程碑月结报表
	 */
	private String milestoneRatePlanUrl;
	/**
	 * 工程计划量调整导出报表访问地址配置
	 */
	private String PlanadjustExportUri;
	/**
	 * 里程碑计划节点调整导出报表访问地址配置
	 */
	private String MilestoneNodePlanAdjustExportUri;
	/**
	 * 里程碑计划节点调整审核导出报表访问地址配置
	 */
	private String MilestoneNodePlanAdjustAuditExportUri;
	/**
	 * 施工匹配框架协议管理
	 */
	private String JobsMatchFrameExportUrl;
	/**
	 * 施工匹配结果汇总表
	 */
	private String milestoneConstructMatchUrl;
	/**
	 * 施工单位匹配结果汇总表
	 */
	private String milestoneConstructUnitMatchUrl;
	/**
	 * 缺陷类型导出
	 */
	private String milestoneDefectTypeUrl;
	/**
	 * 物料类别导出
	 */
	private String milestoneMaterialTypeUrl;
	/**
	 * 物资合同履约问题明细导出
	 */
	private String materialContractProblemUri;
	/**
	 * 物资合同履约问题审核明细导出
	 */
	private String materialContractProblemAuditUri;
	/**
	 * 物资质量问题明细导出
	 */
	private String materialProblemUri;
	/**
	 * 物资质量问题审核明细导出
	 */
	private String materialProblemAuditUri;
	/**
	 * 市公司物资合同履约问题汇总表
	 */
	private String materialSummaryCityUrl;
	/**
	 * 县公司物资合同履约问题汇总表
	 */
	private String materialSummaryCountryUrl;
	/**
	 * 物资合同履约问题明细表
	 */
	private String materialSummaryDetailUrl;
	/**
	 * 物资质量问题报表
	 */
	private String materialQualityProblemUrl;
	/**
	 * 首页图表明细(批次节点完成率)
	 */
	private String milestonePlanExcuteCountBatchExportUri;
	/**
	 * 首页图表明细(单项节点完成率)
	 */
	private String milestonePlanExcuteCountProjectExportUri;
	/**
	 * 首页图表明细(工程量完成率)
	 */
	private String milestoneAccuExcuteCountExportUri;
	/**
	 * 首页图表明细(未竣工工程量完成率)
	 */
	private String milestoneAccuExcuteCountUnfinishExportUri;
	/**
	 * 示范工程导出报表
	 */
	private String exampleProjectExportUrl;
	/**
	 * 示范工程统计报表
	 */
	private String milestoneExampleProjectUrl;
	/**
	 * 配网工程完成率表（供电所）报表
	 */
	private String milestoneCompletePowerhouseUrl;
	/**
	 * 配网工程完成率表（县）报表
	 */
	private String milestoneCompleteCountryUrl;
	/**
	 * 配网工程完成率（市）报表
	 */
	private String milestoneCompleteCityUrl;
	/**
	 * 配网工程完成率表（供电所）报表Copy
	 */
	private String milestoneCompletePowerhouseUrlCopy;
	/**
	 * 配网工程完成率表（县）报表Copy
	 */
	private String milestoneCompleteCountryUrlCopy;
	/**
	 * 配网工程完成率（市）报表Copy
	 */
	private String milestoneCompleteCityUrlCopy;
	/**
	 * 用户反馈问题导出报表
	 */
	private String milestoneUserProblemUrl;
	/**
	 * 台区工程报表访问地址配置
	 */
	private String pmstransProjectUrl;

	public String getPlanningExportUri() {
		return this.appendSignature(this.PlanningExportUri);
	}

	public String getActualingExportUri() {
		return this.appendSignature(this.ActualingExportUri);
	}

	public String getBatchExportUri() {
		return this.appendSignature(this.BatchExportUri);
	}

	public String getProjectExportUri() {
		return this.appendSignature(this.ProjectExportUri);
	}

	public String getMilestonePlanExcuteUri() {
		return this.appendSignature(this.MilestonePlanExcuteUri);
	}

	public String getMilestoneAccuExcuteUri() {
		return this.appendSignature(this.MilestoneAccuExcuteUri);
	}

	public String getMilestoneAppAccuExcuteUri() {
		return this.appendSignature(this.MilestoneAppAccuExcuteUri);
	}

	public String getLightExportUri() {
		return this.appendSignature(this.LightExportUri);
	}

	public String getLightPhoProjectSumUri() {
		return this.appendSignature(this.LightPhoProjectSumUri);
	}

	public String getLightPhoProjectUri() {
		return this.appendSignature(this.LightPhoProjectUri);
	}

	public String getMilestoneBatchNodeExportUri() {
		return this.appendSignature(this.MilestoneBatchNodeExportUri);
	}

	public String getMilestoneBatchAuditExportUri() {
		return this.appendSignature(this.MilestoneBatchAuditExportUri);
	}

	public String getMilestoneProjectNodeExportUri() {
		return this.appendSignature(this.MilestoneProjectNodeExportUri);
	}

	public String getMilestoneMaterialNodeExportUri() {
		return this.appendSignature(this.MilestoneMaterialNodeExportUri);
	}

	public String getProjectCompleteReportUrl() {
		return this.appendSignature(this.ProjectCompleteReportUrl);
	}

	public String getProjectUnCompleteReportUrl() {
		return this.appendSignature(this.ProjectUnCompleteReportUrl);
	}

	public String getMilestoneMonthPlanUrl() {
		return this.appendSignature(this.milestoneMonthPlanUrl);
	}

	public String getMilestoneRatePlanUrl() {
		return this.appendSignature(this.milestoneRatePlanUrl);
	}

	public String getPlanadjustExportUri() {
		return this.appendSignature(this.PlanadjustExportUri);
	}

	public String getMilestoneNodePlanAdjustExportUri() {
		return this.appendSignature(this.MilestoneNodePlanAdjustExportUri);
	}

	public String getMilestoneNodePlanAdjustAuditExportUri() {
		return this.appendSignature(this.MilestoneNodePlanAdjustAuditExportUri);
	}

	public String getJobsMatchFrameExportUrl() {
		return this.appendSignature(this.JobsMatchFrameExportUrl);
	}

	public String getMilestoneConstructMatchUrl() {
		return this.appendSignature(this.milestoneConstructMatchUrl);
	}

	public String getMilestoneConstructUnitMatchUrl() {
		return this.appendSignature(this.milestoneConstructUnitMatchUrl);
	}

	public String getMilestoneDefectTypeUrl() {
		return this.appendSignature(this.milestoneDefectTypeUrl);
	}

	public String getMilestoneMaterialTypeUrl() {
		return this.appendSignature(this.milestoneMaterialTypeUrl);
	}

	public String getMaterialContractProblemUri() {
		return this.appendSignature(this.materialContractProblemUri);
	}

	public String getMaterialContractProblemAuditUri() {
		return this.appendSignature(this.materialContractProblemAuditUri);
	}

	public String getMaterialProblemUri() {
		return this.appendSignature(this.materialProblemUri);
	}

	public String getMaterialProblemAuditUri() {
		return this.appendSignature(this.materialProblemAuditUri);
	}

	public String getMaterialSummaryCityUrl() {
		return this.appendSignature(this.materialSummaryCityUrl);
	}

	public String getMaterialSummaryCountryUrl() {
		return this.appendSignature(this.materialSummaryCountryUrl);
	}

	public String getMaterialSummaryDetailUrl() {
		return this.appendSignature(this.materialSummaryDetailUrl);
	}

	public String getMaterialQualityProblemUrl() {
		return this.appendSignature(this.materialQualityProblemUrl);
	}

	public String getMilestonePlanExcuteCountBatchExportUri() {
		return this.appendSignature(this.milestonePlanExcuteCountBatchExportUri);
	}

	public String getMilestonePlanExcuteCountProjectExportUri() {
		return this.appendSignature(this.milestonePlanExcuteCountProjectExportUri);
	}

	public String getMilestoneAccuExcuteCountExportUri() {
		return this.appendSignature(this.milestoneAccuExcuteCountExportUri);
	}

	public String getMilestoneAccuExcuteCountUnfinishExportUri() {
		return this.appendSignature(this.milestoneAccuExcuteCountUnfinishExportUri);
	}

	public String getExampleProjectExportUrl() {
		return this.appendSignature(this.exampleProjectExportUrl);
	}

	public String getMilestoneExampleProjectUrl() {
		return this.appendSignature(this.milestoneExampleProjectUrl);
	}

	public String getMilestoneCompletePowerhouseUrl() {
		return this.appendSignature(this.milestoneCompletePowerhouseUrl);
	}

	public String getMilestoneCompleteCountryUrl() {
		return this.appendSignature(this.milestoneCompleteCountryUrl);
	}

	public String getMilestoneCompleteCityUrl() {
		return this.appendSignature(this.milestoneCompleteCityUrl);
	}

	public String getMilestoneCompletePowerhouseUrlCopy() {
		return this.appendSignature(this.milestoneCompletePowerhouseUrlCopy);
	}

	public String getMilestoneCompleteCountryUrlCopy() {
		return this.appendSignature(this.milestoneCompleteCountryUrlCopy);
	}

	public String getMilestoneCompleteCityUrlCopy() {
		return this.appendSignature(this.milestoneCompleteCityUrlCopy);
	}

	public String getMilestoneUserProblemUrl() {
		return this.appendSignature(this.milestoneUserProblemUrl);
	}

	public String getPmstransProjectUrl() {
		return this.appendSignature(this.pmstransProjectUrl);
	}
}