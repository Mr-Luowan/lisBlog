<template>
    <div class="full_page">
        <div class="m-content">
            <div>
                <span class="title">Mr Lee博客</span>
            </div>
        
            <div class="maction">
                <el-link href="/blogs">主页</el-link>
                <span>
                    <el-link type="success" href="/blog/add" :disabled="!hasLogin">发表文章</el-link>
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
    </div>
    
</template>

<script>
    export default {
        name: 'Header',
        data() {
            return {
                hasLogin: true,
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
            }

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
        height: 100vh;
        background: no-repeat url('https://mr-luowan.github.io/img/whale.webp');
        background-size:100% 100%;
        background-attachment:fixed;
        background-position: center;
    }
    .m-content {
        display: flex;
        width: 90%;
        margin: auto;
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
</style>