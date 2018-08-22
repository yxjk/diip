// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'babel-polyfill' // 必须在最开始引入垫片
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import store from './store'
import VueParticles from 'vue-particles'
import i18n from './lang' // Internationalization
import * as utils from '@/utils'
import 'font-awesome/less/font-awesome.less'
import '@/views/Layout/style/table.css'
// 引入定制标题的指令
import 'directives/customTitle.js'
// 在入口处引入模拟的假数据
import '@/mock'

// 这里只引入了部分饿了么组件
import {
    Autocomplete,
    Dropdown,
    DropdownMenu,
    DropdownItem,
    Menu,
    Submenu,
    MenuItem,
    MenuItemGroup,
    Input,
    InputNumber,
    Radio,
    RadioGroup,
    RadioButton,
    Checkbox,
    CheckboxGroup,
    Switch,
    Select,
    Option,
    OptionGroup,
    Button,
    ButtonGroup,
    Popover,
    Tooltip,
    Form,
    FormItem,
    Tabs,
    TabPane,
    Tag,
    Table,
    TableColumn,
    Pagination,
    Icon,
    Row,
    Col,
    Card,
    Rate,
    Cascader,
    Loading,
    MessageBox,
    Message,
    Dialog
} from 'element-ui'

Vue.use(Autocomplete)
Vue.use(Dropdown)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(MenuItemGroup)
Vue.use(Input)
Vue.use(InputNumber)
Vue.use(Radio)
Vue.use(RadioGroup)
Vue.use(RadioButton)
Vue.use(Checkbox)
Vue.use(CheckboxGroup)
Vue.use(Switch)
Vue.use(Select)
Vue.use(Option)
Vue.use(OptionGroup)
Vue.use(Button)
Vue.use(ButtonGroup)
Vue.use(Popover)
Vue.use(Tooltip)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Tag)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Pagination)
Vue.use(Icon)
Vue.use(Row)
Vue.use(Col)
Vue.use(Card)
Vue.use(Rate)
Vue.use(Cascader)
Vue.use(Dialog)

Vue.use(Loading.directive)

Vue.prototype.$loading = Loading.service
Vue.prototype.$msgbox = MessageBox
Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$prompt = MessageBox.prompt
Vue.prototype.$message = Message
Vue.prototype.$axios = axios
Vue.use(VueParticles)

// Vue 的全局配置
Vue.config.productionTip = false

// http request 拦截器
axios.interceptors.request.use(
    config => {
        // 如果本地存储中有token字段， 就为所有请求加上Authorization请求头
        if (localStorage.token) {
            config.headers["Authorization"] = `Bearer ${localStorage.token}`
        }
        return config;
    },
    error => {
        console.log(error) // for debug
        Promise.reject(error)
    }
)

// http response 拦截器
axios.interceptors.response.use(
    response => {
        return response
    },
    error => {
        if (error.response) {
            switch (error.response.status) {
              case 400:
                error.response.data.message = '400-请求错误'
                break
              case 403:
                error.response.data.message = '403-拒绝访问'
                break
              case 404:
                error.response.data.message = `404-请求地址出错: ${error.response.config.url}`
                break
              case 408:
                error.response.data.message = '408请求超时'
                break
              case 501:
                error.response.data.message = '501-服务未实现'
                break
              case 502:
                error.response.data.message = '502-网关错误'
                break
              case 503:
                error.response.data.message = '503-服务不可用'
                break
              case 504:
                error.response.data.message = '504-网关超时'
                break
              case 505:
                error.response.data.message = '505-HTTP版本不受支持'
                break

              // 如果后端返回没有权限
              case 401:
                  // 清除token信息并跳转到登录页面
                  localStorage.clear()
                  router.replace("/signin");

              default:
            }
        }
        return Promise.reject(error.response.data) // 返回接口返回的错误信息
    }
)

new Vue({
    el: '#app',
    i18n,
    router,
    store,
    template: '<App/>',
    components: { App }
})
