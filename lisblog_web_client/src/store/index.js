import { createStore } from "vuex"

export default createStore({
    state: {
        token: '',
        userInfo: JSON.parse(sessionStorage.getItem('userInfo'))
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
            localStorage.setItem('token', token)
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo
            localStorage.setItem('userInfo', userInfo)
        },
        REMOVE_INFO: (state) => {
            localStorage.setItem('token', '')
            sessionStorage.setItem('userInfo', JSON.stringify(''))
            state.userInfo = {}
        }
    },
    getters: {
        getUser: state => {
            return state.userInfo
        }
    },
    actions: {},
    modules: {}
})