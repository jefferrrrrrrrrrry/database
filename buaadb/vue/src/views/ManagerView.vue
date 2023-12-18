<template>
    <div class="home" style="height: 100%; display: flex; flex-direction: column; flex: 1;">
      <el-container style="height: 100%; border: 1px solid #eee flex: 1;">
        <el-aside width="sideWidth +'px'" style="background-color: rgb(238, 241, 246); height: 100%; overflow: hidden; box-shadow:2px 0 6px rgba(0,21,41,0.35)">
      <el-menu :default-openeds="['1', '3']" style="height:100%" background-color="rgb(135,206,235)" 
                text-color="#000080" active-text-color="#fff" :collapse-transition="false" :collapse="isCollapse">
         <div style="height:60px; line-height:60px; text-align:center" v-show="collapse">
          <img src="../assets/buaa.png" alt="" style="width:30px; vertical-align:middle;"> 
          <b style="color:rgb(0,0,128); vertical-align:middle;"> BUAA选课系统</b>
        </div>
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
          </el-submenu>
      </el-menu>
    </el-aside>
    
    <el-container style="height: 100%;">
      <el-header style="font-size: 12px;border-bottom: 1px solid #ccc; line-height: 60px; display:flex">
        <div style="flex:1;font-size:18px">
              <span :class="collapseBtnClass" style="cursor:pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width:70px;cursor:pointer">
          <span>王小虎</span>
          <i class="el-icon-arrow-down" style="margin-left:5px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-button type="text" @click="open">个人信息</el-button>
            </el-dropdown-item>
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      
      <el-main >
        <el-breadcrumb separator="/">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item><a href="/">全部课程</a></el-breadcrumb-item>
  </el-breadcrumb>
        <div style="padding:10px 0;display:flex">
          <el-input style="flex:1 width:200px"  placeholder="请输入课程名" suffix-icon="el-icon-search" v-model="s_cname"
    clearable></el-input>
    <el-input style="flex:1 width:200px"  placeholder="请输入课程代码" suffix-icon="el-icon-search" v-model="s_cno"
    clearable></el-input>
    <el-input style="flex:1 width:200px"  placeholder="请输入教师姓名" suffix-icon="el-icon-search" v-model="s_tname"
    clearable></el-input>
          <el-button style="margin-left:5px " type="primary" @click="find()">搜索</el-button>
          <el-button style="margin-left:5px " type="warning" @click="reset()">重置</el-button>
        </div>
        <el-table :data="tableData">
          <el-table-column prop="cno" label="课程代码" width="200">
          </el-table-column>
          <el-table-column prop="cname" label="课程名称" width="200">
          </el-table-column>
          <el-table-column prop="cpos" label="校区" width="200">
          </el-table-column>
          <!--<el-table-column prop="tno" label="上课信息" width="420">
            <template slot-scope="scope">
               使用 v-html 渲染包含换行标签的字符串 
              <span v-html="formatText(scope.row.tno)"></span>
            </template>
          </el-table-column>-->
          <!--<el-table-column prop="department" label="开课院系" width="100">
          </el-table-column>-->
          <el-table-column prop="ccredit" label="学分" width="170">
          </el-table-column>
          <el-table-column prop="cremain" label="剩余人数" width="220">
          </el-table-column>
          <el-table-column prop="ccapacity" label="容量" width="220">
          </el-table-column>
            <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="del(scope.$index, scope.row)">退选</el-button>
        </template>
      </el-table-column>
  
        </el-table>
        <div style="padding:10px 0">
          <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5,10,15,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
            :total="total">
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
    </div>
  </template>
  
  <script>
  // @ is an alias to /src
  import HelloWorld from '@/components/HelloWorld.vue'
  import request from '@/utils/request'
  
  export default {
    name: 'ManagerView',
    data(){
      return {
        tableData: [],
        total:0,
        isCollapse:false,
        sideWidth:200,
        collapseBtnClass:'el-icon-s-fold',
        currentPage:1,
        pageSize:10,
        s_cname:"",
        s_cno:"",
        s_tname:"",
        dialogVisible: false
      }
    },
    created(){
      this.load()
    },
    methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      request.get("http://localhost:9090/manager/").then(res=>{
        console.log(res.data);
        this.tableData=res.data;
        this.total=res.length;
      })
    },
    find(){
      request.get("http://localhost:9090/manager/find",{
        params:{
          cno:this.s_cno,
          cname:this.s_cname,
          tname:this.s_tname
        }
      }).then(res=>{
        console.log(res.data);
        this.tableData=res.data;
        this.total=res.length;
      })
    },
    del(id){
        
    },
    formatText(text) {
      // 将换行符 \n 替换为 <br>
      return text.replace(/\n/g, "<br>");
    },
    reset(){
      this.s_cname="";
      this.s_cno="";
      this.s_tname
    },
    collapse(){
      this.isCollapse=!this.isCollapse;
      if(this.isCollapse){
        this.sideWidth=64;
      }
      if(this.collapseBtnClass=='el-icon-s-fold'){
        this.collapseBtnClass='el-icon-s-unfold'
      }else{
        this.collapseBtnClass='el-icon-s-fold'
      }
    },handleSizeChange(pageSize){
      console.log(pageSize);
      this.pageSize=pageSize;
      this.load();
    },handleCurrentChange(currentPage){
    console.log(currentPage);
    this.currentPage=currentPage;
    this.load();
  },open() {
      this.$alert('这是一段内容', '标题名称', {
        confirmButtonText: '确定'
      });
    }
  }
  }
  </script>
  