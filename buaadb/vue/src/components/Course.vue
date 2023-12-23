<script>
import request from "@/utils/request";

export default {
  name: "Course",
  methods: {
    // 将字符串中的换行符替换为 HTML 换行标签
    load(){
      request.get("http://localhost:9090/course/").then(res=>{
        //console.log(res.data);
        this.tableData=res.data;
        this.total=res.length;
      });
    },reset(){
      this.s_cname="";
      this.s_cno="";
      this.s_tname;
      this.find();
    },
    find(){
      request.get("http://localhost:9090/course/find",{
        params:{
          cno:this.s_cno,
          cname:this.s_cname,
          tname:this.s_tname,
          pageNum:this.currentPage,
          pageSize:this.pageSize
        }
      }).then(res=>{
        //console.log(res.data);
        this.tableData=res.data.page;
        this.total=res.data.total;
      });
    },
    del(id){
      var out_cno=id;
      request.post("http://localhost:9090/course/withdraw",out_cno).then(res=>{
        if(res){
          this.$message.success("退选成功");
          this.load();
        }else{
          this.$message.error("退选失败");
        }
      })
    },
    choose(id){
      var out_cno=id;
      request.post("http://localhost:9090/course/selectCourse",out_cno).then(res=>{
        if(res){
          this.$message.success("选课成功");
          this.load();
        }else{
          this.$message.error("选课失败")
        }
      })

    },
    formatText(text) {
      // 将换行符 \n 替换为 <br>
      return text.replace(/\n/g, "<br>");
    },
    handleSizeChange(pageSize){
      //console.log(pageSize);
      this.pageSize=pageSize;
      this.find();
    },handleCurrentChange(currentPage){
      //console.log(currentPage);
      this.currentPage=currentPage;
      this.find();
    },handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleLoad() {
      this.fileLoadVisible = true
      this.form = {}
    },
    handleEdit(row) {
    //this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },save(){
      //this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = false
    },saveFile(){
      this.fileLoadVisible=false;
    },handleClose(){
      _ => {
        done();
      }
      this.fileLoadVisible=false;
      this.dialogFormVisible = false;
    },exports(){
      window.open("http://localhost:9090/course/export");
    }
  },
  data(){
    return{
      tableData: [],
      total:0,
      currentPage:1,
      pageSize:2,
      s_cname:"",
      s_cno:"",
      s_tname:"",
      dialogVisible: false,
      path:"",
      id:"",
      loc:0,
      page:1,
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      },
      dialogFormVisible:false,
      fileLoadVisible:false,
      form:{},

    }
  },
  created() {
    //console.log(this.$route);
    //this.path=this.$router.options.routes[0].path+"/"+this.$router.options.routes[0].children[index-1].path;
    this.find();
    if(this.$route.path.includes("teacher")){
      this.id="teacher";
      this.loc=1;
    }else if(this.$route.path.includes("student")){
      this.id="student";
      this.loc=0;
    }else{
      this.id="manager";
      this.loc=2;
    }
  }
}
</script>

<template>
  <div>
    <el-breadcrumb separator="/" >
      <el-breadcrumb-item :to='{ path: "/${id}" } ' >首页</el-breadcrumb-item>
      <el-breadcrumb-item v-if="this.loc==0"><a href='/${id}/course' >选课</a></el-breadcrumb-item>
      <el-breadcrumb-item v-if="this.loc==1"><a href='/${id}/course' >全部课程</a></el-breadcrumb-item>
    </el-breadcrumb>
    <div style="padding:10px 0;display:flex">
      <el-input style="flex:1;width:200px"  placeholder="请输入课程名" suffix-icon="el-icon-search" v-model="s_cname"
                clearable></el-input>
      <el-input style="flex:1; width:200px"  placeholder="请输入课程代码" suffix-icon="el-icon-search" v-model="s_cno"
                clearable></el-input>
      <el-input style="flex:1 ;width:200px"  placeholder="请输入教师姓名" suffix-icon="el-icon-search" v-model="s_tname"
                clearable></el-input>
      <el-button style="margin-left:5px " type="primary" @click="find()">搜索</el-button>
      <el-button style="margin-left:5px " type="warning" @click="reset()">重置</el-button>
      <el-button type="primary" @click="handleAdd" v-if="loc==2||loc==3">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="handleLoad" v-if="loc==2||loc==3">文件上传 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="primary" @click="exports" >课程导出 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>
    <el-table :data="tableData">
      <el-table-column prop="cno" label="课程代码" width="150">
      </el-table-column>
      <el-table-column prop="cname" label="课程名称" width="200">
      </el-table-column>
      <el-table-column prop="ctype" label="课程类型" width="150">
      </el-table-column>
      <el-table-column prop="tname" label="开课老师" width="150">
      </el-table-column>
      <el-table-column prop="cpos" label="校区" width="150">
      </el-table-column>
      <el-table-column prop="ccredit" label="学分" width="120">
      </el-table-column>
      <el-table-column prop="cremain" label="剩余人数" width="80">
      </el-table-column>
      <el-table-column prop="ccapacity" label="容量" width="80">
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope" >
          <el-button v-if="loc==0"
              size="mini"
              type="danger"
              @click="choose(scope.row.cno)">选课</el-button>
          <el-button v-if="loc==1"
              size="mini"
              type="danger"
              @click="del(scope.row.cno)">取消开课</el-button>
          <el-button v-if="loc==2"
              size="mini"
              type="primary"
              @click="del(scope.row.cno)">更改信息</el-button>
          <el-button v-if="loc==2"
              size="mini"
              type="danger"
              @click="del(scope.row.cno)">删除课程</el-button>
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

  <el-dialog title="课程信息" :visible.sync="dialogFormVisible" width="30%" :before-close="handleClose">
    <el-form label-width="80px" size="small">
      <el-form-item label="课程代码">
        <el-input v-model="form.cno" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="课程名称">
        <el-input v-model="form.cname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="课程类型">
        <el-input v-model="form.ctype" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="教师姓名">
        <el-input v-model="form.tname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="校区">
        <el-input v-model="form.cpos" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="学分">
        <el-input v-model="form.credit" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="剩余人数">
        <el-input v-model="form.cremain" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="容量">
        <el-input v-model="form.ccapacity" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="save">确 定</el-button>
      <el-button @click="dialogFormVisible = false">取 消</el-button>
    </div>
  </el-dialog>
    <el-dialog title="课程信息" :visible.sync="fileLoadVisible" width="30%" :before-close="handleClose">
      <div style="display: flex; justify-content: center; align-items: center;width: 100%;">
        <el-form label-width="80px" size="small" >
          <el-upload class="upload-demo" drag action="https://jsonplaceholder.typicode.com/posts/" multiple accept=".xls, .xlsx">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            <div class="el-upload__tip" slot="tip">只能上传excel文件，且不超过500kb</div>
          </el-upload>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="saveFile">确 定</el-button>
        <el-button @click="fileLoadVisible = false">取 消</el-button>
      </div>
    </el-dialog>






  </div>
</template>

<style scoped>

</style>