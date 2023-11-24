<template>
  <div class="home" style="height:100%">
    <el-container style="height: 100%; border: 1px solid #eee">
  <el-aside width="sideWidth +'px'" style="background-color: rgb(238, 241, 246); height: 100% overflow: hidden;">
    <el-menu :default-openeds="['1', '3']" style="height:100%" background-color="rgb(0,0,0)" 
              text-color="#fff" active-text-color="#ffd04b" :collapse-transition="false" :collapse="isCollapse">
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-message"></i>
          <span>导航一</span>
        </template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="1-1">选项1</el-menu-item>
          <el-menu-item index="1-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="1-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="1-4">
          <template slot="title">选项4</template>
          <el-menu-item index="1-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>导航一</span>
        </template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="2-1">选项1</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="2-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span>导航一</span>
        </template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="3-1">选项1</el-menu-item>
          <el-menu-item index="3-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="3-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="3-4">
          <template slot="title">选项4</template>
          <el-menu-item index="3-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
    </el-menu>
  </el-aside>
  
  <el-container>
    <el-header style="font-size: 12px;border-bottom: 1px solid #ccc; line-height: 60px; display:flex">
      <div style="flex:1;font-size:18px">
            <span :class="collapseBtnClass" style="cursor:pointer" @click="collapse"></span>
      </div>
      <el-dropdown style="width:70px;cursor:pointer">
        <span>王小虎</span>
        <i class="el-icon-arrow-down" style="margin-left:5px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item>删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>
    
    <el-main>
      <el-table :data="tableData">
        <el-table-column prop="number" label="序号" width="60" >
        </el-table-column>
        <el-table-column prop="id" label="课程代码" width="120">
        </el-table-column>
        <el-table-column prop="name" label="课程名称" width="120">
        </el-table-column>
        <el-table-column prop="address" label="校区" width="120">
        </el-table-column>
        <el-table-column prop="information" label="上课信息" width="450">
          <template slot-scope="scope">
            <!-- 使用 v-html 渲染包含换行标签的字符串 -->
            <span v-html="formatText(scope.row.information)"></span>
          </template>
        </el-table-column>
        <el-table-column prop="form" label="课程类别" width="100">
        </el-table-column>
        <el-table-column prop="nature" label="课程性质" width="130">
        </el-table-column>
        <el-table-column prop="department" label="开课院系" width="130">
        </el-table-column>
        <el-table-column prop="credits" label="学分" width="70">
        </el-table-column>
        <el-table-column prop="remaining" label="剩余人数/容量" >
          <template slot-scope="scope">
            <!-- 使用 v-html 渲染包含换行标签的字符串 -->
            <span v-html="formatText(scope.row.remaining)"></span>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  data(){
    const item = {
        number:'1',
        id:'B3I063120',
        name:'编译技术',
        address:'学院路校区',
        information:'[课内实验]张莉,杨海燕◇\n[10-17周]星期一第6,7,8节◇机房,[2-9周]星期一第6,7,8节◇机房\n[主任务]张莉◇\n[1-13周]星期一第3,4节◇E207,[1-13周]星期三第1,2节◇E207',
        form:'必修',
        nature:'核心专业类',
        department:'计算机学院',
        credits:'4.5',
        remaining:'容量:117/120\n对外:0/0',
    };
    return {
      tableData: Array(10).fill(item),
      isCollapse:false,
      sideWidth:200,
      collapseBtnClass:'el-icon-s-fold'
    }
  },
  methods: {
  // 将字符串中的换行符替换为 HTML 换行标签
  formatText(text) {
    // 将换行符 \n 替换为 <br>
    return text.replace(/\n/g, "<br>");
  },
  collapse(){
    this.isCollapse=!this.isCollapse;
    if(this.isCollapse){
      this.sideWidth=64;
    }
  }
},
}
</script>
