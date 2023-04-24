<template>
    <div class="ocrForm">
        <!-- 退出按钮 -->
        <div class="box" @click="$emit('doClose')">X</div>
        <!-- form表单 -->
        <div class="form">
            <van-form label-width="100px">
                <van-field v-model="f.model.realName" name="realName" label="RealName" placeholder="realName"
                    type="textarea" :rules="[{ required: true, }]" />

                <van-field v-model="f.model.idCard" name="idCard" label="IdCard" placeholder="idCard"
                    :rules="[{ required: true }]" />

                <van-field v-model="f.model.taxRegNumber" name="TaxRegNumber" label="TaxRegNumber"
                    placeholder="taxRegNumber" :rules="[{ required: true }]" />


                <van-field readonly clickable name="datetimePicker" :value="value" label="Birthday"
                    placeholder="Please select a birthday" @click="showPicker = true" v-model="birthday" />
                <van-popup v-model="showPicker" position="bottom">
                    <van-datetime-picker v-model="currentDate" :min-date="minDate" :max-date="maxDate" type="date"
                        confirm-button-text="Confirm" cancel-button-text="Cancel" title="Please select a birthday"
                        @confirm="doConfirm" />
                </van-popup>


                <div class="btn" @click="onSubmit">Submit</div>
            </van-form>
        </div>
    </div>
</template>

<script>
import isNext from './isNext.js'
import { uploadUserInfoAPI } from "../../api";
import { add, unt } from "../../utils/AES.js";
export default {
    props: ['user', 'isupload'],
    data() {
        return {
            value: '',
            showPicker: false,
            minDate: new Date(1900, 0, 1),
            maxDate: new Date(Date.now()),
            //当前选中的时间
            currentDate: new Date(this.formatDate(this.user.birthDay)),
            birthday: '',
            f: {
                model: {
                    birthDay: this.timeFunc(this.formatDate(this.user.birthDay)),
                    idCard: this.user.idCard,
                    realName: this.user.realName,
                    taxRegNumber: this.user.taxRegNumber,
                    idCardImageFront: this.isupload.sfzqImg,
                    idCardImageBack: this.isupload.sfzhImg,
                    idCardImagePan: this.isupload.bankImg
                }
            }
        }
    },
    methods: {
        doConfirm(value) {
            this.showPicker = false
            const timer = new Date(value).getTime()
            this.birthday = this.formatDate(timer)
            //将时间戳赋值给表单的数据
            this.f.model.birthDay = timer
            console.log(timer, '时间戳')
            console.log(value, 'value')
            console.log(this.birthday, 'this.birthday')
        },
        //表单提交按钮
        async onSubmit() {
            console.log(this.f, '提交用户信息的表单')
            const res = await uploadUserInfoAPI(add(this.f))
            console.log(unt(res.data), '上传证件结果')
            isNext()
        },
        //时间戳转成年月日
        formatDate(timestamp) {
            const date = new Date(timestamp);
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');
            return `${year}-${month}-${day}`;
        },
        timeFunc(value) {//value= 2018-04-04
            let str = value.replace(/-/g, '/');
            let date = new Date(str);
            return date.getTime() // 1000
        }
    },
    mounted() {
        this.birthday = this.formatDate(this.user.birthDay)
    }
}
</script>
<style lang="less" scoped>
/deep/.van-cell,
.van-field {
    margin-top: (10/@a);
    border-radius: (20/@a);
}

.ocrForm {
    max-width: (320/@a);
    background-color: #f5f5f5;
    padding-top: (10/@a);
    padding-bottom: (20/@a);

    .box {
        position: absolute;
        top: (10/@a);
        right: (5/@a);
        width: (40/@a);
        height: (40/@a);
        background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
        border-radius: 50%;
        text-align: center;
        line-height: (40/@a);
        font-size: (20/@a);
        color: #fff;
    }

    .form {
        .btn {
            background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
            border-radius: (10/@a);
            margin-top: (20/@a);
            margin-left: (45/@a);
            width: (230/@a);
            height: (44/@a);
            color: #fff;
            text-align: center;
            line-height: (44/@a);
            font-size: (20/@a);
        }
    }
}
</style>