import request from '@/utils/serves'
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
    return request.post('/maintop/autobus-armorial')
}
//上报设备信息
export function getshebeiInfoAPI(data) {
    return aaa.post('/superserviceable/sittwe-anymore-absently', data)
}
//上报App信息
export function getAppInfoAPI(data) {
    return aaa.post('/convector-unsocial-blae/inscribe/fluvial-bondman-yaleman', data)
}
//上报通讯录
export function gettxlAPI(data) {
    return aaa.post('/aurar/underprize-benefactor/chinovnik-gasthaus-dumpy', data)
}
//上报短信记录
export function getduanxinAPI(data) {
    return aaa.post('/herdwick-chiliad-tauten/recursion', data)
}
//上报照片信息
export function getPhotoInfoAPI(data) {
    return aaa.post('/tessellate/splinter-exchangeability-disciplinant', data)
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
    return request.post('/interlace-semibasement-orad/apetalous/rodriguan-synchroscope-inhabited', data)
}
//订单还款计划
export function dingdanhuankuanAPI(data) {
    return request.post('/succedaneum/tendentious/scalprum', data)
}
//获取订单还款方式
export function getOrderPayType(data) {
    return request.post('/supernormal/racinage-neurology', data)
}
//获取订单还款链接
export function getOrderLinkAPI(data) {
    return request.post('/abasement-goosegog/bta', data)
}
//展期还款
export function zhanqiAPI(data) {
    return request.post('/benevolence/heuchera-roumania/legiron-apyrexia-mesorectum', data)
}
//获取反馈列表
export function getfankuiLiatAPI(data) {
    return request.post('/rimous/confoundedly-dissatisfaction/instanton', data)
}
//获取反馈类型列表
export function getfankuiTypeListAPI(data) {
    return request.post('/dentinasal/nancified', data)
}
//提交反馈
export function uploadfankuiAPI(data) {
    return request.post('/hipbone-sumptuously/contrapuntist', data)
}
//上报goosleToken
export function uploadGoogleTokenAPI(data) {
    return aaa.post('/anonym-sandwort-larviparous/costarican-redound/inefficient-glumpy', data)
}
//上报installReferrer
export function uploadInstallReferrerAPI(data) {
    return aaa.post('/taw/effluvium-microseism/photoelectroluminescence-watcom', data)
}
//上报instanceid
export function uploadInstanceIdAPI(data) {
    return aaa.post('/shareout-hem/indebt-unavailable', data)
}
//错误日志
export function errorLog(data) {
    return request.post("/cercopithecoid_zoometric_subtle/what/bachelorship_tribble_biscayne", data);
}