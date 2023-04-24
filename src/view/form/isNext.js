import router from "../../router";
import store from '../../store'
export default async function () {
    await store.dispatch('form/weiwancehngform')
    const form = store.state.form.formData
    let item = {};
    for (var i in form) {
        item = form[i] || {};
        break;
    }
    if (item.formType == "OCR") {
        router.push('/ocr')
        //跳转OCR
        return;
    } else if (item.formType == "ALIVE") {
        router.push('/huoti')
        return
    } else if (item.formType == "BASIC") {
        router.push('/baseForm')
        //条表单
        return
    } else {
        router.push('/productList')
    }
}