<!-- 左侧导航组件 -->
<!-- 使用说明：<side-bar></side-bar> -->
<template>
    <div id="sidebar-wrap" :class="{ collapsed: toggSiderBar }">
        <h4 class="logo">
          <span class="rythm twist1">{{toggSiderBar ?$t('m.login.short_name'): $t('m.login.introduction')}}</span>
        </h4>
        <el-menu background-color="#324157" text-color="#ddd" :default-active="$route.path" :unique-opened="isUnique" :router="isRouter" mode="vertical" :collapse="toggSiderBar">
            <template v-for="item in menu">
                <el-submenu v-if="item.children.length !== 0" :index="item.id" :route="item.router">
                    <template slot="title">
                        <i :class="item.icon"></i>
                        <span slot="title">{{langType === 'en'? item.nameEn: item.name}}</span>
                    </template>
                    <el-menu-item v-for="child in item.children" :index="child.id" :route="child.router">
                        <i :class="child.icon"></i>
                        <span slot="title">{{langType === 'en'? child.nameEn: child.name}}</span>
                    </el-menu-item>
                </el-submenu>

                <el-menu-item v-else :index="item.id" :route="item.router">
                    <i :class="item.icon"></i>
                    <span slot="title">{{langType === 'en'? item.nameEn: item.name}}</span>
                </el-menu-item>

            </template>
        </el-menu>
    </div>
</template>
<script>
import bus from "@/bus";

export default {
  name: "sidebar",
  data() {
    return {
      isUnique: true,
      isRouter: true,
      menu: localStorage.menu ? JSON.parse(localStorage.menu) : []
    };
  },
  computed: {
    toggSiderBar() {
      return this.$store.state.common.isCollapse;
    },
    langType() {
      return this.$i18n.locale;
    }
  },
  methods: {
    toggleDance() {
    }
  }
};
</script>
<style scoped lang="less">
#sidebar-wrap {
  width: 160px;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
  bottom: 0;
  z-index: 5;

  /* 图标动画 */
  .imgWrap {
    text-align: center;
    position: absolute;
    bottom: 30px;
    width: 100%;
    .gif {
      width: 60px;
      height: 60px;
      border-radius: 30px;
      &:hover {
        cursor: pointer;
      }
    }
  }
  .logo {
    color: #fff;
    text-align: center;
    background: #324157;
    padding: 18px 0;
    margin: 0;
  }
}

// 动态改变左侧导航包裹层宽度
#sidebar-wrap.collapsed {
  width: 64px;
}
.el-menu {
  height: 100%;
}

// 美化左侧导航的留白
.el-submenu .el-menu-item {
  padding: 0 20px;
  min-width: 160px;
  padding-left: 20px !important;
}

</style>
