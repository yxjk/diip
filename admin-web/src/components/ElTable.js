import {Table, Pagination} from 'element-ui';
import Vue from 'vue';

export default {
  name: 'ElTablePage',
  template: `<div>
                    <el-table :data="sourceData" v-loading="loading"
                      @sort-change="handleSortChange" stripe >
                        <slot></slot>
                    </el-table>
                    <div style="height:12px"></div>
                    <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleIndexChange"
                        :page-size="pageSize"
                        :page-sizes="[10, 20, 30]"
                        :current-page="currentPage"
                        layout="total,sizes, prev, pager, next,jumper"
                        :total="sourceTotal">
                    </el-pagination>
               </div>`,
  props: {
    url: String,
    params: Object
  },
  created: function () {
    this.fetch();
  },
  data: function () {
    return {
      sourceData: [],
      loading: true,
      sourceTotal: 100,
      currentPage: 1,
      pageSize: 10
    }
  }, methods: {
    handleSizeChange: function (size) {
      this.pageSize = size;
      this.currentPage = 1;
      this.fetch(this.currentPage, size);
    }, handleIndexChange: function (index) {
      this.currentPage = index;
      this.fetch(index, this.pageSize);
    }, handleSortChange: function(column){
      this.fetch(this.currentPage, this.pageSize, column.prop, column.order);
    }, search: function(){
      this.fetch(1, this.pageSize);
    },fetch: function (index, size, sortBy, order) {
      this.loading = true;
      this.sourceData = [];
      this.sourceTotal = 0;

      let send = this.params || {};
      send.currentPage = index || 1;
      send.pageSize = size || 10;
      send.sortBy = sortBy || send.sortBy;
      send.order = order || send.order;
      this.$axios({
        url: this.url,
        method: "POST",
        data: send
      }).then(res => {
        if(res.status!="200"){
          this.$message.error("服务异常["+res.status+"]，请重试");
          return false;
        }
        if(res.data.status!="S"){
          this.$message.error(res.data.msg);
          return false;
        }
        this.sourceData = res.data.data;
        this.sourceTotal = res.data.total;
        setTimeout(() => {
          this.loading = false;
        }, 0.5 * 1000)
      }).catch(error=>{
        this.$message.error(error);
      });
    }
  }
}
