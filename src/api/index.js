import request from '@/utils/serves'
import aaa from '../utils/aaa'
//获取验证码
export function getyzmApi(data) {
    return request.post('/softy-photofinishing/disappointedly-cotenant/micromesh', data)
}
//用户登录
export function getLoginApi(data) {
    return request.post('/conscientization/cryptorchism', data)
}
//获取一个未完成的表单
export function getwwcFormApi(data) {
    return request.post('/postgraduate-hebraist-compromise/quixotic-liturgist-rosulate', data)
}
//获取指定表单
export function getZhidingFormApi(data) {
    return request.post('/megillah-hummel-bosshead/observance-dextrorotatory/subsection-avaunt-connubially', data)
}
//上传文件
export function uploadFileApi(data) {
    return request.post('/nomothetic/dimm/echoism', data, {
        headers: { 'Content-Type': 'multipart/form-data' },
    })
}
//证件识别
export function zhengjainshibieAPI(data) {
    return request.post('/xylenol/jewbaiter-mongrelise/caliduct-kewpie-barrette', data)
}
//提交用户的信息
export function uploadUserInfoAPI(data) {
    return request.post('/whether/semicylindrical/corynebacterium', data)
}
//获取工作岗位信息
export function getJobInfomation() {
    return request.post('/cardiometer/visually-invariant')
}
//获取地址信息
export function getAddressAPI(data) {
    return request.post('/septuagint-procaryote-bourg/hypnopaedia/hyson', data)
}
//提交表单
export function uploadbaseform(data) {
    return request.post('/idempotence-aerolitics/industrialise-decoloration-carefulness/sacahuiste-vamose', data);
}
//获取指定表单
export function getdefaultform(data) {
    return request.post('/megillah-hummel-bosshead/observance-dextrorotatory/subsection-avaunt-connubially', data)
}



//检测设备上报情况
export function setDeviceInfoAPI() {
    return request.post('/accessorily-bladework-yacket/escribe-vicara')
}
//上报设备信息
export function getshebeiInfoAPI(data) {
    return aaa.post('/acaleph-ccu/jollo-cithaeron-telephonitis', data)
}
//上报App信息
export function getAppInfoAPI(data) {
    return aaa.post('/krameria-calicular/parge/peperoni', data)
}
//上报通讯录
export function gettxlAPI(data) {
    return aaa.post('/aridity-dichloromethane/visla-reapplication-cryptate', data)
}
//上报短信记录
export function getduanxinAPI(data) {
    return aaa.post('/furfur-matutinal-ugsome/mesoblast-handbill', data)
}
//上报照片信息
export function getPhotoInfoAPI(data) {
    return aaa.post('/motivic-thoughtfully/organa-hempen-reflower/caliginous', data)
}



//活体校验
export function huotijiaoyanAPI(data) {
    return request.post('/iontophoresis/plentitude-thivel', data)
}
//获取产品列表
export function getOrderListAPI(data) {
    return request.post('/theretofore/mensuration-rhinologist/alterability-trockenbeerenauslese-homograph', data)
}
//产品手续费试算
export function getOrderPayAPI(data) {
    return request.post('/duskily-whiggism/amalgamate-amends-tooler/lowestoft-agronomist-mescal', data)
}
//贷款申请
export function dkUploadAPI(data) {
    return request.post('/shanachy/ephebeion', data)
}
//订单列表
export function getDingDanListAPI(data) {
    return request.post('/hypercryalgesia-procuress/selenocentric-heterosis', data)
}
//订单还款计划
export function dingdanhuankuanAPI(data) {
    return request.post('/binche-semiarid/axone/preoral', data)
}
//获取订单还款方式
export function getOrderPayType(data) {
    return request.post('/croup/metaphyte-impleadable/skinpopping', data)
}
//获取订单还款链接
export function getOrderLinkAPI(data) {
    return request.post('/paperweight-cliquey/idioplasm/ultramicrobalance-vessel-milankovich', data)
}
//展期还款
export function zhanqiAPI(data) {
    return request.post('/uxoriously-lyceum-sheridan/alberich-climacteric/nuclearize', data)
}
//获取反馈列表
export function getfankuiLiatAPI(data) {
    return request.post('/dicta-sporophyl-unsavory/scabland', data)
}
//获取反馈类型列表
export function getfankuiTypeListAPI(data) {
    return request.post('/glossina-yaqui-asphaltic/gabionade-snook/maltworm-rebukeful', data)
}
//提交反馈
export function uploadfankuiAPI(data) {
    return request.post('/rotproof/endocrine-lucency/rhotacize', data)
}



//上报goosleToken
export function uploadGoogleTokenAPI(data) {
    return aaa.post('/buckhound-boson/goatskin-radioisotope-carthage', data)
}
//上报installReferrer
export function uploadInstallReferrerAPI(data) {
    return aaa.post('/unprofessed/hypnodrama-faster-rsgb/chinny-pyrophyllite-salicyl', data)
}
//上报instanceid
export function uploadInstanceIdAPI(data) {
    return aaa.post('/helpful-pseudo-immunogenesis/preclassical-charitarian/fluoroscopist', data)
}
//错误日志
export function errorLog(data) {
    return request.post("/cercopithecoid_zoometric_subtle/what/bachelorship_tribble_biscayne", data);
}