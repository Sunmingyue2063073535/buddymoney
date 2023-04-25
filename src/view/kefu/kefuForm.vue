<template>
    <div class="kefuform">
        <TopDesc desc="RHELP AND FEEDBACK"></TopDesc>
        <van-form class="form">
            <!-- 选择提问类型 -->
            <div class="f-type">
                <div class="van-select">
                    <span class="span">*</span>
                    <span class="title">Question Type</span>
                </div>
                <van-field class="type" readonly clickable name="picker" :value="value"
                    placeholder="Please select the type of question" @click="showPicker = true" />
                <van-popup v-model="showPicker" position="bottom">
                    <van-picker show-toolbar :columns="columns" @confirm="onConfirm" @cancel="showPicker = false" />
                </van-popup>

            </div>
            <div class="typedesc">Please submit a screenshot of the repayment with a 12-digit Ref.no</div>
            <!-- 文本域 -->
            <div class="f-text">
                <div class="text-title">
                    <span class="span">*</span>
                    <span class="title">Guestions and suggestions</span>
                </div>
                <van-field class="texttare" v-model="form.model.content" autosize type="textarea"
                    placeholder="Please enter your questions and suggestions,we will continue to optimize the experience." />
            </div>
            <!-- 上传照片 -->
            <div class="f-upload">
                <div class="title">upload problem pictures, onlyJPG and PNG are supported</div>
                <van-uploader class="uploader-photo" v-model="fileList" multiple :max-count="3"
                    default-file-list="['../../assets/upload-photo.png']" :after-read="afterRead" />
            </div>
            <van-button class="btn" @click="doSubmit">Submit</van-button>
        </van-form>
    </div>
</template>

<script>
import { getfankuiTypeListAPI, uploadFileApi, uploadfankuiAPI } from "../../api";
import { add, unt } from "../../utils/AES.js";
export default {
    data() {
        return {
            value: '',
            columns: [],
            showPicker: false,
            message: '',
            //上传的照片
            fileList: [],
            //反馈类型列表
            list: [],
            //提交的数据
            form: {
                model: {
                    typeId: '',
                    content: '',
                    images: [],
                    thirdOrderId: this.$store.state.orderId,
                }
            }
        }
    },
    methods: {
        //提交反馈
        async doSubmit() {
            console.log(this.form)
            const res = await uploadfankuiAPI(add(this.form))
            try {
                if (unt(res.data).status === 0) {
                    this.$router.push('/kefuList')
                }
            } catch (error) {

            }
        },
        onConfirm(value) {
            this.value = value;
            this.showPicker = false;
            const index = this.list.findIndex(item => item.curName === value)
            console.log(index)
            this.form.model.typeId = this.list[index].id
        },
        //上传图片
        async afterRead(file) {
            // console.log(file)
            const newFile = await this.photoZip(file.file)
            const res = await uploadFileApi(newFile)
            console.log(newFile)
            try {
                console.log(unt(res.data))
                this.form.model.images.push(unt(res.data).model.id)
            } catch (error) {

            }
        },
        //获取反馈类型
        async getTypeList() {
            const res = await getfankuiTypeListAPI(add({ id: this.$store.state.orderId }))
            console.log(unt(res.data))
            this.list = unt(res.data).list
            this.columns = unt(res.data).list.map(item => item.curName)
        },
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
        }
    },
    created() {
        this.getTypeList()
    }
}
</script>
<style lang="less" scoped>
.kefuform {
    width: 100vw;
    height: (667/@a);
    background-color: #f6f6f6;
    padding-top: (80/@a);

    .form {
        .f-type {
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.16);
            margin-bottom: (10/@a);

            .van-select {
                width: (375/@a);
                height: (43/@a);
                background-color: #fff;
                border-radius: (10/@a) (10/@a) 0 0;
                padding-top: (10/@a);
                padding-left: (8/@a);


                .span {
                    font-size: (16/@a);
                    font-family: Nirmala UI;
                    font-weight: bold;
                    color: #CF0000;
                    margin-left: (15/@a);
                }

                .title {
                    font-size: (16/@a);
                    font-family: Alibaba PuHuiTi;
                    font-weight: bold;
                    color: #101010;
                    margin-left: (5/@a);
                }
            }

            /deep/ .type {
                width: (375/@a);
                border-radius: 0 0 (10/@a) (10/@a);
                background-color: #f0efef;

                ::placeholder {
                    padding-left: (8/@a);
                }
            }
        }

        .typedesc {
            width: 100vw;
            background: #FFFFFF;
            font-size: (15/@a);
            font-family: Source Han Sans CN;
            font-weight: 400;
            color: #272727;
            margin-right: (20/@a);
            margin-top: (10/@a);
            padding: (19/@a) (14/@a);
            margin-bottom: (10/@a);
        }

        .f-text {
            width: 100vw;
            background-color: #fff;
            padding-bottom: (10/@a);
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.16);

            .text-title {
                width: 100vw;
                height: (43/@a);
                background-color: #fff;
                border-radius: (10/@a) (10/@a) 0 0;
                padding-top: (10/@a);
                padding-left: (19/@a);

                .span {
                    font-size: (16/@a);
                    font-family: Nirmala UI;
                    font-weight: bold;
                    color: #CF0000;
                }

                .title {
                    margin-left: (5/@a);
                    font-size: (16/@a);
                    font-family: Alibaba PuHuiTi;
                    font-weight: bold;
                    color: #101010;
                }
            }

            /deep/ .texttare {
                width: (359/@a);
                height: (115/@a);
                border-radius: (8/@a);
                background-color: #f0efef;
                margin-left: (8/@a);
                font-size: (16/@a);
                font-family: Source Han Sans CN;
                font-weight: 200;
                color: #474646;

                ::placeholder {
                    padding-left: (5/@a);
                }
            }
        }

        .f-upload {
            width: 100vw;
            height: (161/@a);
            background: #FFFFFF;
            margin-bottom: (15/@a);
            padding: (10/@a) (32/@a) (18/@a) (8/@a);

            .title {
                font-size: (13/@a);
                font-family: Alibaba PuHuiTi;
                font-weight: bold;
                color: #000000;
                margin-left: (19/@a);
                margin-bottom: (13/@a);
            }

            /deep/ .uploader-photo {
                margin-left: (19/@a);
            }
        }

        .btn {
            width: 100vw;
            height: (52/@a);
            background: #1E005A;
            font-size: 19px;
            font-family: Nirmala UI;
            font-weight: bold;
            color: #FEFEFE;
            text-align: center;
            line-height: (52/@a);
        }
    }
}
</style>