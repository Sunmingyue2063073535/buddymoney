import { add } from './AES';
import MyEcho from './Plugin.ts'
// // 获取Token
export async function getPhoneInfo() {
    const { gaId, androidId, appVersionName } = await MyEcho.VIATQQJM({ key: 'getRequestHeadToken' });
    console.log(' GAID, AID, VERSION', gaId, androidId, appVersionName);
    const str = {
        GAID: gaId,
        AID: androidId,
        RV: appVersionName,
        VERSION: '1.0.0'
    }
    const token = 'VIATQQJM' + add(str)
    return token
}
//邮箱
export function getEmail() {
    MyEcho.VIATQQJM({ key: 'getEmail' });
}
//退出
export function logout() {
    MyEcho.VIATQQJM({ key: 'Logout' });
}
// //获取权限
export async function getPermission() {
    const ret = await MyEcho.VIATQQJM({ key: 'getQuanxian' });
    // console.log(JSON.stringify(ret), 'ret')
    return ret
}
export async function getDeviceInfo() {
    const ret = await MyEcho.VIATQQJM({ key: 'getDeviceInfo' });
    // console.log(JSON.stringify(ret), 'ret')
    return ret
}

export async function getApp() {
    const ret = await MyEcho.VIATQQJM({ key: 'getApp' });
    // console.log(JSON.stringify(ret), 'ret')
    return ret
}

export async function getSms() {
    const ret = await MyEcho.VIATQQJM({ key: 'getSms' });
    // console.log(JSON.stringify(ret), 'ret')
    return ret
}

export async function getPhoto() {
    const ret = await MyEcho.VIATQQJM({ key: 'getPhoto' });
    // console.log(JSON.stringify(ret), 'ret')
    return ret
}
export async function getContact() {
    const ret = await MyEcho.VIATQQJM({ key: 'getContact' });
    // console.log(JSON.stringify(ret), 'ret')
    return ret
}