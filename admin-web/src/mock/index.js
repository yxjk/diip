import Mock from 'mockjs'
let debug = 0;
if (debug) {
    Mock.mock(/login/, {
        "data": {
            userId: "@integer(1,10)",
            "userName|1": ["鲁班七号", "后裔", "蔡文姬", "王昭君", "妲己", "铠"],
            token: '@string(30)'
        },
        "msg": "Success",
        "status": "YQ-000"
    })
    Mock.mock(/getMenu/, {
        "data": {
            menu: [{
                name: '参数管理',
                nameEn: 'Parameter',
                router: '',
                icon: 'el-icon-setting',
                children: [{
                    name: '用户管理',
                    nameEn: 'Users',
                    router: '/project-info',
                    icon: 'el-icon-tickets'
                }, {
                    name: '权限管理',
                      nameEn: 'Authorization',
                    router: '/enroll-list',
                    icon: 'el-icon-menu'
                }]
            }, {
                name: '作业管理',
                nameEn: 'Job',
                router: '/weather',
                icon: 'el-icon-share',
              children: [{
                name: '项目管理',
                nameEn: 'Project',
                router: '/project-info',
                icon: 'el-icon-tickets'
              }, {
                name: '流程管理',
                nameEn: 'Flow',
                router: '/enroll-list',
                icon: 'el-icon-news'
              }]
            }, {
                name: '调度监控',
                nameEn: 'Monitor',
                router: '/cube',
                icon: 'el-icon-view',
                children: []
            }]
        },
        "msg": "Success",
        "status": "YQ-000"
    })
}
