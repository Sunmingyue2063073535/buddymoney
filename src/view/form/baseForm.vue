<template>
    <div class="workInfo">
        <!-- 顶部 -->
        <FormTopDesc :desc="titleName"></FormTopDesc>
        <!-- 表单 -->
        <van-form @submit="doSubmit">
            <!-- 表单解构 -->
            <div v-for="item in               formList              " :key="item.id">
                <van-field v-if="item.type === 'text'" :rules="[{ required: true, }]"
                    v-model="form.model.submitData[item.id]" :name="item.name" :label="item.name"
                    :placeholder="`Please enter ${item.name}`" />


                <!-- <van-field v-else-if="item.type === 'text' && item.id === 'email'"
                    :rules="[{ pattern, message: 'please enter your vaild email', required: true }]"
                    v-model="form.model.submitData[item.id]" :name="item.name" :label="item.name"
                    :placeholder="`Please enter ${item.name}`" /> -->



                <van-field type="digit" v-else-if="item.type === 'number'" :rules="[{ required: true, }]"
                    v-model.number="form.model.submitData[item.id]" :name="item.name" :label="item.name"
                    :placeholder="`Please enter ${item.name}`" />
                <!-- 工作类型 -->
                <div v-else-if="item.type === 'jobType'">
                    <van-field :rules="[{ required: true, }]" v-model="fieldValue" is-link readonly label="Occupation"
                        :placeholder="`Please select the ${item.name}`" @click="show = true" />
                    <van-popup :rules="[{ required: true, }]" v-model="show" round position="bottom">
                        <van-cascader v-model="cascader" title="Please select the region" :options="list"
                            placeholder="Please select" @close="show = false" @finish="Finish"
                            :field-names="{ text: 'name', value: 'id', children: 'children' }" />
                    </van-popup>
                </div>
                <!-- 住址类型 -->
                <div v-else-if="item.type === 'addressType'">
                    <div class="gsaddress">
                        <van-field :rules="[{ required: true, }]" v-model="address.fieldValue" is-link readonly
                            :label="item.name" placeholder="Please select the Company Address"
                            @click="address.show = true" />
                        <van-popup v-model="address.show" round position="bottom">
                            <van-cascader v-model="address.cascader" title="Please select the region"
                                :options="address.list" placeholder="Please select" @close="address.show = false"
                                @finish="(event) => { addressFinish(event, item.id) }"
                                :field-names="{ text: 'name', value: 'id' }" @change="addressChange" />
                        </van-popup>
                    </div>
                </div>
                <!-- 详细地址 -->
                <div v-else-if="item.id === 'address.detailAddress'">
                    <van-field :rules="[{ required: true, }]" v-model="form.model.submitData[item.id]" :name="item.name"
                        :label="item.name" placeholder="Please enter the detailed address" />
                </div>
                <!-- 选择框 -->
                <div v-if="item.type === 'select'">
                    <van-field readonly clickable :name="item.name" v-model="form.model.submitData[item.id]" :value="value"
                        :label="item.name" :placeholder="`Please select ${item.name}`" @click="doClick(item)"
                        :rules="[{ required: true, }]" />
                    <van-popup v-model="showPicker" position="bottom">
                        <van-picker show-toolbar :columns="columns" @confirm="Confirm" confirm-button-text="Confirm"
                            cancel-button-text="Cancel" @cancel="showPicker = false" />
                    </van-popup>
                </div>
                <!-- 联系人 -->
                <div v-for="ele in               listdata              " :key="ele.id" class="list"
                    v-if="item.type === 'contact'">
                    <div class="title">{{ ele.mes }}</div>
                    <van-field v-model="form.model.submitData.userEmergs[ele.id].name" name="用户名" label="name"
                        placeholder="Name" :rules="[{ required: true, }]" />
                    <van-field type="digit" v-model="form.model.submitData.userEmergs[ele.id].phone" name="phone"
                        label="phone" placeholder="Example:9000000000" :rules="[{ required: true, }]" />
                    <!-- 选择关系 -->
                    <van-field :rules="[{ required: true, }]" readonly clickable name="relation"
                        v-model="form.model.submitData.userEmergs[ele.id].relation" :value="value" label="relation"
                        placeholder="relationship" @click="showPicker = true; index = ele.id" />
                    <van-popup v-model=" showPicker " position="bottom">
                        <van-picker confirm-button-text="Confirm" cancel-button-text="Cancel" show-toolbar
                            :columns=" gxlist " @confirm=" doConfirm " @cancel=" showPicker = false " />
                    </van-popup>
                </div>
            </div>
            <!-- 提交按钮 -->
            <van-button class="formBtn" type="button">Submit</van-button>
        </van-form>
    </div>
</template>

<script>
import { getJobInfomation, getAddressAPI, uploadbaseform, getdefaultform, getdevinceInfo } from "../../api/index.js";
import { add, unt } from "../../utils/AES.js";
import { Toast } from 'vant'
import isNext from './isNext.js'
export default {
    name: 'workInfo',
    data() {
        return {
            pattern: /^([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_.-]+).([a-zA-Z]{2,5})$/,//邮箱的正则
            a: '',
            titleName: '',
            detailAddress: '',
            show: false,
            fieldValue: '',
            list: [],
            cascader: '',
            //表单list
            formList: [],
            form: {
                model: {
                    formId: "",//表单id
                    submitData: {
                        professionInfo: {
                            workType: null,
                            profession: null
                        },
                        address: {
                            bigAddress: {
                                province: null,
                                city: null
                            },
                            detailAddress: null
                        },
                        companyAddress: {
                            bigAddress: {
                                province: null,
                                city: null
                            },
                            detailAddress: null
                        }
                    }
                },
            },

            listdata: [
                { id: 0, mes: 'Emergency contact 1' },
                { id: 1, mes: 'Emergency contact 2' },
                { id: 2, mes: 'Emergency contact 3' }
            ],
            type: {
                text: 'name',
                value: 'id',
                children: 'children'
            },
            address: {
                fieldValue: '',
                show: false,
                cascader: '',
                list: []
            },
            value: '',
            showPicker: false,
            columns: [],
            //关系列表
            gxlist: []
        }
    },
    methods: {
        // 当工作选完成时
        Finish({ selectedOptions }) {
            this.show = false
            console.log('selectedOptions', selectedOptions)
            this.fieldValue = selectedOptions.map((option) => option.name).join('/');
            const arr = selectedOptions.map((option) => option.id).join('/').split('/');
            try {
                this.form.model.submitData.professionInfo.workType = arr[0]
                this.form.model.submitData.professionInfo.profession = arr[1]
            } catch (error) {

            }
        },
        //获取工作信息
        async getworkinfo() {
            const res = await getJobInfomation()
            console.log('工作信息', unt(res.data))
            this.list = unt(res.data).model
        },
        //获取地址信息
        async getaddress() {
            const r = {
                model: ''
            }
            const res = await getAddressAPI(add(r))
            unt(res.data).model.forEach(item => {
                this.address.list.push({
                    id: item.id,
                    name: item.name,
                    children: item.children || null
                })
            })
            // console.log(this.address.list, '省')
            // console.log('地址信息', unt(res.data))
        },
        //当地址信息变化时
        async addressChange({ value, selectedOptions, tabIndex }) {
            const r = {
                model: value
            }
            const res = await getAddressAPI(add(r))
            // console.log('市', unt(res.data))
            // console.log('value, selectedOptions, tabIndex', value, selectedOptions, tabIndex)
            selectedOptions.forEach(item => {
                if (item.id === value) {
                    item.children = unt(res.data).model
                }
            })
            console.log('地址数据', this.address.list)
        },
        //当关系选择完毕时执行
        doConfirm(ele, value) {
            console.log(ele, value, 'ele,value')
            this.form.model.submitData.userEmergs[this.index].relation = ele
            this.showPicker = false
        },
        //公司地址选完市触发
        addressFinish({ selectedOptions }, id) {
            selectedOptions.forEach(item => {
                if (item.children) {
                    this.address.show = false
                }
            })
            this.address.fieldValue = selectedOptions.map((option) => option.name).join('/');
            try {
                let value = {
                    province: selectedOptions[0] && selectedOptions[0].id || '',
                    city: selectedOptions[1] && selectedOptions[1].id || ''
                };
                this.onChange(id, value)
                // let value = { province: selectedOptions[0].id || '', city: selectedOptions[1].id || '' };
                // onChange(id, value)
            } catch (error) {
            }

        },
        onChange(key, value) {
            if (key.indexOf(".") > -1) {
                let strs = key.split(".");
                let m1 = this.form.model.submitData[strs[0]];
                if (m1 == null) {
                    m1 = {};
                }
                m1[strs[1]] = value;
                this.form.model.submitData[strs[0]] = m1;
            }
        },
        //提交按钮
        async doSubmit() {
            for (let k in this.formList) {
                let item = this.formList[k];
                if (item.type == "select") {
                    let name = this.form.model.submitData[item.id] || "";
                    let option = item.options.filter(item => name == item.name)[0];
                    if (option) {
                        this.form.model.submitData[item.id] = option.id;
                    }
                }
                if (item.type == "contact") {
                    for (let index in this.form.model.submitData.userEmergs) {
                        let contact = this.form.model.submitData.userEmergs[index] || {};
                        let relation = this.relationList.filter(r => r.name == contact.relation)[0];
                        if (relation) {
                            this.form.model.submitData.userEmergs[index].relation = relation.id;
                        }
                    }
                }
                this.onChange(item.id, this.form.model.submitData[item.id])
            }
            const formData = this.$store.state.form.formData
            const arr = formData.filter(item => item['formName'] === this.titleName)
            this.form.model.formId = formData[0].formId || ''
            const res = await uploadbaseform(add(this.form))
            if (!res) return
            const result = unt(res.data)
            Toast('Submitted successfully')
            await this.$store.dispatch('form/weiwancehngform')
            this.getForm()
            isNext()
            // this.$router.push('/personInformation')
            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });
        },
        //获取指定表单
        async getForm() {
            const f = {
                model: {
                    formId: '',
                    nodeType: ''
                }
            }
            f.model.formId = this.$store.state.form.formData[0].formId || ''
            const res = await getdefaultform(add(f))

            try {
                console.log(res)
                console.log('工作指定表单', unt(res.data))
                this.formList = unt(res.data).model.forms[0].content

                let submitData = unt(res.data).model.baseInfo || {};

                let item = this.formList.filter(item => item.type == "contact")[0];
                if (item) {
                    submitData.userEmergs = [
                        {},
                        {},
                        {},
                    ]
                }
                if (!submitData.professionInfo) {
                    submitData.professionInfo = this.form.model.submitData.professionInfo;
                }
                if (!submitData.address) {
                    submitData.address = this.form.model.submitData.address;
                }
                if (!submitData.companyAddress) {
                    submitData.companyAddress = this.form.model.submitData.companyAddress;
                }
                this.form.model.submitData = submitData;
                let pp = unt(res.data).model.forms[0].content[0] || {};
                this.gxlist = pp.props && pp.props.relationList && pp.props.relationList.map(item => item.name)
                this.relationList = pp.props && pp.props.relationList && pp.props.relationList;
            } catch (error) {

            }
        },
        //点击文本框触发
        doClick(item) {
            this.showPicker = true
            this.columns = item.options.map(item => item.name)
            console.log(this.columns)
            this.i = item
        },
        //点击确认按钮之后
        Confirm(value, index) {
            this.showPicker = false
            this.form.model.submitData[this.i.id] = value
        },
        //获取地址信息
        async getaddress() {
            const r = {
                model: ''
            }
            const res = await getAddressAPI(add(r))
            unt(res.data).model.forEach(item => {
                this.address.list.push({
                    id: item.id,
                    name: item.name,
                    children: item.children || null
                })
            })
            console.log(this.address.list, '省')
            console.log('地址信息', unt(res.data))

        },
    },
    async created() {
        //标题
        this.titleName = this.$store.state.form.formData[0].formName || ''
        this.$store.dispatch('form/weiwancehngform')
        this.getworkinfo()
        //获取地址信息
        this.getaddress()
        //获取指定表单
        this.getForm()
        console.log(this.form.model.submitData.userEmergs, 'user')

    }
}
</script>
<style lang="less" scoped>
@a: 3.75vw;

html,
body {
    scroll-behavior: smooth;
}

/deep/ .van-field {
    border: (2/@a) #53378c solid;
    border-radius: (20/@a);
    margin-top: (10/@a);
}

.workInfo {
    width: 100vw;
    min-height: (667/@a);
    background-color: #f5f5f5;
    padding-top: (80/@a);
    box-sizing: border-box;
    padding-left: (10/@a);
    padding-right: (10/@a);
    padding-bottom: (30/@a);

    .top {
        height: (56/@a);
        background-color: #fff;
        width: 100vw;
        display: flex;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 9;

        .callback {
            margin-top: (20/@a);
            margin-left: (15/@a);
            width: (10/@a);

            img {
                width: 100%;
            }
        }

        .top-title {
            margin-left: (100/@a);
            margin-top: (17/@a);
            font-family: 'Inter';
            font-style: normal;
            font-weight: 400;
            font-size: (18/@a);
            color: #202020;
        }
    }

    .formBtn {
        width: (232/@a);
        height: (44/@a);
        background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
        font-size: (19/@a);
        font-family: Alibaba PuHuiTi;
        font-weight: bold;
        color: #FFFFFF;
        border-radius: (10/@a);
        margin-top: (20/@a);
        margin-left: (70/@a);
    }
}
</style>