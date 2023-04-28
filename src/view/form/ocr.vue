<template>
    <div class="ocr">
        <div class="ocr-img">
            <img src="../../assets/ocr.png" alt="">
        </div>
        <!-- 返回键 -->
        <div class="callback" @click="$router.push('/shouye')"></div>

        <!-- 身份证钱 -->
        <div class="sfzq" @click="doSfzq">
            <input class="sfz" id="sfzqian" type="file" accept="image/*" capture="camera" @change="handleFileInputqian"
                style="width:50px'">
            <img :src="sfzqImg" alt="">
        </div>
        <!-- 身份证后 -->
        <div class="sfzh" @click="doSfzh">
            <input class="sfz" id="sfzhou" type="file" accept="image/*" capture="camera" @change="handleFileInputsfhou">
            <img :src="sfzhImg" alt="">
        </div>
        <!-- 银行卡 -->
        <div class="bank" @click="doBank">
            <input class="sfz" id="bank" type="file" accept="image/*" capture="camera" @change="handleFileInputBank">
            <img :src="bankImg" alt="">
        </div>
        <!-- 提交按钮 -->
        <div class="btn" @click="doSubmit">CONFIRM</div>
        <!-- 确认信息组件 -->
        <van-dialog v-model="show" title="OCR FORM" :show-cancel-button="false" v-if="show" :show-confirm-button="false">
            <ocrForm :user="user" :isupload="isupload" @doClose="doClose"></ocrForm>
        </van-dialog>
    </div>
</template>
<script>
import ocrForm from './ocrForm'
import { uploadFileApi, zhengjainshibieAPI } from "../../api";
import { add, unt } from "../../utils/AES.js";
import { Toast } from "vant";
export default {
    components: { ocrForm },
    data() {
        return {
            sfzqImg: require('../../assets/sfzq.png'),
            sfzhImg: require('../../assets/sfzh.png'),
            bankImg: require('../../assets/bank.png'),
            user: {
            },
            isupload: {
                sfzqImg: '',
                sfzhImg: '',
                bankImg: ''
            },
            show: false
        }
    },
    methods: {
        //提交按钮
        doSubmit() {
            if (!this.isupload.sfzImg && !this.isupload.sfzhImg && !this.isupload.bankImg) {
                Toast('Please upload documents')
            } else {
                this.show = true
            }
        },
        //身份证钱
        handleFileInputqian(event) {
            this.$store.commit('showLoading')
            // 获取图片元素的引用
            const photo = this.$refs.photo
            // 获取用户选择的文件对象
            const file = event.target.files[0]
            // 创建一个FileReader对象，用于读取文件内容
            const reader = new FileReader()
            // 当文件读取完成后，触发onload事件
            reader.onload = async () => {
                // 将读取的文件内容设置为图片的src属性
                // photo.src = reader.result
                this.$store.commit('showLoading')
                const newFile = this.base64toFile(reader.result, file.name)
                const fileObj = {
                    content: reader.result,
                    file: newFile,
                    message: '',
                    status: ''
                }
                const newFileObj = await this.photoZip(fileObj.file)
                console.log(newFileObj, '1')
                //压缩后的
                const res = await uploadFileApi(newFileObj)
                console.log(unt(res.data), '上传图片的结果身份证前')
                if (unt(res.data).status == 0) {
                    //识别证件的结果
                    console.log(unt(res.data))
                    this.user = Object.assign({ ... await this.zjsb(unt(res.data).model.id, 'FRONT') }, this.user)
                    this.sfzqImg = unt(res.data).model.id
                    this.isupload.sfzqImg = unt(res.data).model.id
                    this.user.sfqian = unt(res.data).model.id
                }
            }
            // 开始读取文件内容，这会触发onload事件
            reader.readAsDataURL(file)
        },
        doSfzq() {
            document.getElementById('sfzqian').click()
        },
        // 身份证后
        handleFileInputsfhou(event) {
            this.$store.commit('showLoading')
            // 获取图片元素的引用
            const photo = this.$refs.photo
            // 获取用户选择的文件对象
            const file = event.target.files[0]
            // 创建一个FileReader对象，用于读取文件内容
            const reader = new FileReader()
            // 当文件读取完成后，触发onload事件
            reader.onload = async () => {
                this.$store.commit('showLoading')
                // 将读取的文件内容设置为图片的src属性
                // photo.src = reader.result

                const newFile = this.base64toFile(reader.result, file.name)
                const fileObj = {
                    content: reader.result,
                    file: newFile,
                    message: '',
                    status: ''
                }
                const newFileObj = await this.photoZip(fileObj.file)
                console.log(newFileObj, '1')
                //压缩后的
                const res = await uploadFileApi(newFileObj)
                if (unt(res.data).status == 0) {
                    this.user = Object.assign({ ...await this.zjsb(unt(res.data).model.id, 'BACK') }, this.user)
                    console.log(unt(res.data), '上传图片的结果身份证前')
                    this.sfzhImg = unt(res.data).model.id
                    this.isupload.sfzhImg = unt(res.data).model.id
                    // this.user.sfqian = unt(res.data).model.id
                }

            }
            // 开始读取文件内容，这会触发onload事件
            reader.readAsDataURL(file)
        },
        doSfzh() {
            document.getElementById('sfzhou').click()
        },
        //银行卡
        handleFileInputBank(event) {
            this.$store.commit('showLoading')
            // 获取图片元素的引用
            const photo = this.$refs.photo
            // 获取用户选择的文件对象
            const file = event.target.files[0]
            // 创建一个FileReader对象，用于读取文件内容
            const reader = new FileReader()
            // 当文件读取完成后，触发onload事件
            reader.onload = async () => {
                // 将读取的文件内容设置为图片的src属性
                // photo.src = reader.result
                this.$store.commit('showLoading')
                const newFile = this.base64toFile(reader.result, file.name)
                const fileObj = {
                    content: reader.result,
                    file: newFile,
                    message: '',
                    status: ''
                }
                const newFileObj = await this.photoZip(fileObj.file)
                console.log(newFileObj, '1')
                //压缩后的
                const res = await uploadFileApi(newFileObj)
                if (unt(res.data).status == 0) {
                    this.user = Object.assign({ ...await this.zjsb(unt(res.data).model.id, 'PAN') }, this.user)
                    console.log(unt(res.data), '上传图片的结果身份证前')
                    this.bankImg = unt(res.data).model.id
                    this.isupload.bankImg = unt(res.data).model.id
                    // this.user.sfqian = unt(res.data).model.id
                }

            }
            // 开始读取文件内容，这会触发onload事件
            reader.readAsDataURL(file)
        },
        doBank() {
            document.getElementById('bank').click()
        },
        //base64转成file对象
        base64toFile(base64String, fileName) {
            const binaryString = atob(base64String.split(',')[1]);
            const buffer = new ArrayBuffer(binaryString.length);
            const bytes = new Uint8Array(buffer);

            for (let i = 0; i < binaryString.length; i++) {
                bytes[i] = binaryString.charCodeAt(i);
            }

            const blob = new Blob([buffer], { type: 'image/png' });
            return new File([blob], fileName, { type: 'image/png' });
        },
        // 压缩照片
        async photoZip(file) {
            return new Promise((resolve, reject) => {
                const reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = () => {
                    const base64 = reader.result;
                    const img = new Image();
                    img.src = base64;
                    img.onload = () => {
                        const canvas = document.createElement('canvas');
                        const context = canvas.getContext('2d');
                        canvas.width = img.width;
                        canvas.height = img.height;
                        context.drawImage(img, 0, 0);
                        const quality = 0.7;
                        const maxWidth = 800;
                        const maxHeight = 800;
                        let newBase64 = canvas.toDataURL('image/jpeg', quality);
                        console.log(newBase64.length / 1024, '11111111111111')
                        while (newBase64.length / 1024 > 100) {
                            canvas.width *= 0.9;
                            canvas.height *= 0.9;
                            context.drawImage(img, 0, 0, canvas.width, canvas.height);
                            newBase64 = canvas.toDataURL('image/jpeg', quality);
                        }
                        const blob = this.dataURLtoBlob(newBase64);
                        const newFile = new File([blob], file.name, { type: 'image/jpeg' });
                        console.log(newBase64.length / 1024, '33333333')
                        // 返回file对象格式
                        resolve({ file: newFile, content: newBase64, message: '', status: '' });
                    }
                }
                reader.onerror = reject;
            }
            );
        },
        dataURLtoBlob(dataURL) {
            const arr = dataURL.split(',');
            const mime = arr[0].match(/:(.*?);/)[1];
            const bstr = atob(arr[1]);
            let n = bstr.length;
            const u8arr = new Uint8Array(n);
            while (n--) {
                u8arr[n] = bstr.charCodeAt(n);
            }
            return new Blob([u8arr], { type: mime });
        },
        //证件识别
        async zjsb(file, cardType) {
            const form = {
                model: {
                    url: file,
                    cardType: cardType
                }
            }
            console.log(form, '证件识别的参数')
            const res = await zhengjainshibieAPI(add(form))
            if (unt(res.data).status == 0) {
                console.log(unt(res.data), '证件识别的结果')
                return unt(res.data).model
            }
            return
        },
        // 关闭弹窗
        doClose() {
            this.show = false
        }
    }
}
</script>
<style lang="less" scoped>
.ocr {
    .sfz {
        display: none;
    }

    .sfzq {
        position: absolute;
        top: (130/@a);
        left: (11/@a);
        width: (164/@a);
        height: (95/@a);

        // background-color: red;
        img {
            width: (164/@a);
            height: (95/@a);
        }
    }

    .sfzh {
        position: absolute;
        top: (130/@a);
        right: (7/@a);
        width: (164/@a);
        height: (95/@a);

        // background-color: green;
        img {
            width: (164/@a);
            height: (95/@a);
        }
    }

    .bank {
        position: absolute;
        top: (243/@a);
        left: (11/@a);
        width: (164/@a);
        height: (95/@a);

        // background-color: purple;
        img {
            width: (164/@a);
            height: (95/@a);
        }
    }



    .btn {
        position: absolute;
        bottom: (17/@a);
        left: (51/@a);
        width: (274/@a);
        height: (52/@a);
        background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
        opacity: 1;
        text-align: center;
        line-height: (52/@a);
        border-radius: (10/@a);
        font-size: (29/@a);
        font-family: Source Han Sans CN;
        font-weight: 500;
        color: #FFFFFF;
    }

    .callback {
        position: absolute;
        top: (36/@a);
        left: (18/@a);
        width: (20/@a);
        height: (20/@a);
    }

    .ocr-img {
        position: absolute;
        top: 0;
        left: 0;
        width: 100vw;
        height: (667/@a);

        img {
            width: 100%;
        }
    }

}
</style>