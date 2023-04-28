<template>
    <div class="huoti">
        <FormTopDesc desc="FACE RECOGNITION"></FormTopDesc>
        <div class="ht-title">PLEASE SHAKE YOUR HEAD FROM SIDE TO SIDE</div>
        <div class="video-div">
            <video ref="video" width="236" height="236" autoplay class="video"></video>
        </div>
        <canvas ref="canvas" width="236" height="236" style="display: none;"></canvas>
        <div class="btn" @click="photograph">start identifying</div>
    </div>
</template>
<script>
import { add, unt } from '../../utils/AES.js'
import { huotijiaoyanAPI } from "../../api";
import isNext from './isNext.js'
import { Toast } from "vant";
export default {
    data() {
        return {
            headImgSrc: require('../../assets/app-anquan.png'),
            formData: {
                model: {
                    faceInfo: null,
                    livenessType: 'ACC_H5'
                }
            },
        }
    },
    methods: {
        // 调用摄像头
        callCamera() {
            // H5调用电脑摄像头API
            navigator.mediaDevices.getUserMedia({
                video: true
            }).then(success => {
                // 摄像头开启成功
                this.$refs['video'].srcObject = success
                // 实时拍照效果
                this.$refs['video'].play()
            }).catch(error => {
                console.error('摄像头开启失败，请检查摄像头是否可用！')
            })
        },
        // 拍照
        async photograph() {
            let ctx = this.$refs['canvas'].getContext('2d')
            // 把当前视频帧内容渲染到canvas上
            ctx.drawImage(this.$refs['video'], 0, 0, 300, 300)
            // 转base64格式、图片格式转换、图片质量压缩
            let imgBase64 = this.$refs['canvas'].toDataURL('image/jpeg', 0.7)
            console.log(imgBase64)
            // 由字节转换为KB 判断大小
            let str = imgBase64.replace('data:image/jpeg;base64,', '')
            let strLength = str.length
            let fileLength = parseInt(strLength - (strLength / 8) * 2)
            // 图片尺寸  用于判断
            let size = (fileLength / 1024).toFixed(2)
            // console.log(size)
            this.dialogCamera = false
            //上传
            this.formData.model.faceInfo = str
            const res = await huotijiaoyanAPI(add(this.formData))
            console.log(this.formData)
            try {
                console.log(res, 'res')
                console.log(unt(res.data), '活体')
                if (unt(res.data).status === 0) {
                    console.log(res, 'res222')
                    Toast('Successful recognition')
                    isNext()
                }
            } catch (error) {

            }
            // let ADOM = document.createElement('a')
            // ADOM.href = this.headImgSrc
            // ADOM.download = new Date().getTime() + '.jpeg'
            // ADOM.click()
        },
        // 关闭摄像头
        closeCamera() {
            if (!this.$refs['video'].srcObject) {
                this.dialogCamera = false
                return
            }
            let stream = this.$refs['video'].srcObject
            let tracks = stream.getTracks()
            tracks.forEach(track => {
                track.stop()
            })
            this.$refs['video'].srcObject = null
        },
    },
    created() {
        this.callCamera()
        // 解决视频卡顿
        const constraints = {
            audio: true,
            video: {
                width: { ideal: 640 },
                height: { ideal: 480 },
                frameRate: { ideal: 30 }
            }
        };

        navigator.mediaDevices.getUserMedia(constraints)
            .then(stream => {
                this.$refs.videoPlayer.srcObject = stream;
                this.$refs.videoPlayer.play();
            })
            .catch(error => console.error(error));
    }
}
</script>
<style lang="less" scoped>
.huoti {
    width: 100vw;
    min-height: (667/@a);
    background-color: #f5f5f5;
    box-sizing: border-box;
    padding-top: (100/@a);

    .ht-title {
        font-size: (13/@a);
        font-family: Source Han Sans CN;
        font-weight: 400;
        color: #000000;
        margin-left: (45/@a);
    }

    .video-div {
        margin-top: (30/@a);
        margin-left: (69/@a);
        width: (236/@a);
        height: (236/@a);
        border-radius: 50%;
        font-size: 0;

        .video {
            transform: scaleX(-1);
            object-fit: fill;
            width: (236/@a);
            height: (236/@a);
            // border-radius: (150/@a);
            border-radius: (200/@a);
        }
    }

    .btn {
        position: absolute;
        bottom: (30/@a);
        left: (67/@a);
        width: (241/@a);
        height: (53/@a);
        text-align: center;
        line-height: (53/@a);
        background: linear-gradient(180deg, #8B70C0 0%, #1E005A 100%);
        border-radius: (10/@a);
        font-size: (19/@a);
        font-family: Alibaba PuHuiTi;
        font-weight: bold;
        color: #FFFFFF;
    }
}
</style>