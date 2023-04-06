<template>
    <div class="full_page">
        <p class="text">Hello World</p>
        <div class="m-content">
            <div>
                <!-- <span class="title">Mr Lee博客</span> -->
            </div>
        
            <div class="maction">
                <el-link href="/blogs">主页</el-link>
                <span>
                    <el-link type="success" href="/blog/add" :disabled="hasLogin">发表文章</el-link>
                </span>
                <span v-show="!hasLogin">
                    <el-link type="primary" href="/login">登录</el-link>
                </span>
                <span v-show="hasLogin">
                    <el-link type="danger" @click="logout">退出</el-link>
                </span>
                <div>
                    <el-avatar :size="50" :src="user.avatar"></el-avatar>
                    <div>{{ user.userName }}</div>
                </div>
            </div>
        </div>

        <div class="arrow_down">
            <a href="#target" class="iconfont scroll-down-effects">&#xe63c;</a>
        </div>
    </div>
    <div id="target"></div>
    
</template>

<script>
    export default {
        name: 'Header',
        data() {
            return {
                hasLogin: false,
                user: {
                    user: '请先登录',
                    avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
                },
                blogs: {},
                currentPage: 1,
                total: 0
            }
        },
        methods: {
            logout() {
                const _this = this
                this.$axios.get('/api/user/logout', {
                    headers: {
                        'Authorization': localStorage.getItem('token')
                    }
                }).then((res) => {
                    _this.$store.commit('REMOVE_INFO')
                    _this.$router.push('/login')
                })
            }, 
            goDown() {
                window.scrollTo(0, document.documentElement.scrollHeight)
            },
            showText(showElDom,str='',speed=100){
                if(!showElDom){
                    console.error('该元素不存在');
                    return;
                }
                
                let strArr = str.trim().split("");
                let newStr = '';
                let len = strArr.length;
                let number = 0;
                
                let timer = setInterval(() => {
                    if (number > len-1) {
                        clearInterval(timer);
                        return;
                    }
                
                    newStr += strArr[number];
                    showElDom.innerText = newStr;
                
                    number++;
                }, 100);
            }
        },
        mounted() {
            this.showText(document.querySelector("p"),'Hello World');
        },
        created() {
            var userInfo = this.$store.getters.getUser
            if(userInfo && userInfo.userName) {
                this.user.userName = userInfo.userName
                this.user.avatar = userInfo.avatar
                this.hasLogin = true
            }
        }
    }
</script>

<style scoped>
 
    .full_page {
        flex-direction: column;
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 100vh;
        background: no-repeat url('https://mr-luowan.github.io/img/whale.webp');
        background-size:100% 100%;
        background-attachment:fixed;
        background-position: center;
        background-clip: padding-box;
    }
    .m-content {
        display: flex;
        width: 90%;
        height: 70px;
        align-items: center;
        justify-content: space-between;
    }
    .maction {
        display: flex;
        align-items: center;
    }
    .title {
        color: white;
        font-size: 20px;
        font-weight: bold;
    }
   
    .scroll-down-effects {
        font-size: 200px;
        color: white;
        line-height: 3;
        text-align: center;
        text-shadow: 2px 2px 4px rgba(0,0,0,0.15);
    }

    /* CDN 服务仅供平台体验和调试使用，平台不承诺服务的稳定性，企业客户需下载字体包自行发布使用并做好备份。 */
    @font-face {
        font-family: 'iconfont';  /* Project id 3988139 */
        src: url('http://at.alicdn.com/t/c/font_3988139_44blzcd4dn5.woff2?t=1680226953981') format('woff2'),
            url('http://at.alicdn.com/t/c/font_3988139_44blzcd4dn5.woff?t=1680226953981') format('woff'),
            url('http://at.alicdn.com/t/c/font_3988139_44blzcd4dn5.ttf?t=1680226953981') format('truetype');
    }
    .iconfont{
        font-family:"iconfont" !important;
        font-size:32px;font-style:normal;
        -webkit-font-smoothing: antialiased;
        -webkit-text-stroke-width: 0.2px;
        -moz-osx-font-smoothing: grayscale;
    }
    
    .text {
        height: 100vh;
        width: 100%;
        background-image: url('https://mr-luowan.github.io/img/whale.webp');
        background-size: 100% 100%;
        background-position: center;
        font-size: 150px;
        text-transform: uppercase;
        text-align: center;
        line-height: 200px;
        position: absolute;
        padding-top: 250px;
        -webkit-background-clip: text;
        color: transparent;
        pointer-events: none;
        cursor: default;
    }

</style>