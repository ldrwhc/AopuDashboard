<template>
  <div style="border: 1px solid #ccc; margin-top: 40px">
    <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editor"
        :defaultConfig="toolbarConfig"
    />
    <!-- 编辑器 -->
    <Editor
        style="height: 350px; overflow-y: hidden"
        :defaultConfig="editorConfig"
        :value="modelHtml"
        @onChange="onChange"
        @onCreated="onCreated"
    />
  </div>
</template>

<script>
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";

export default {
  name: "RichTextEditor.vue",
  components: { Editor, Toolbar },
  props: {
    modelHtml :{
      type:String,
      default:''
    }
  },
  data() {
    return {
      editor: null,
      initFinished:false,
      toolbarConfig: {
        // toolbarKeys: [ /* 显示哪些菜单，如何排序、分组 */ ],
        excludeKeys: [ 'headerSelect', 'italic', 'group-more-style' ,'group-image','group-video'],
      },
      editorConfig: {
        placeholder: "请输入内容...",
        // autoFocus: false,

        // 所有的菜单配置，都要在 MENU_CONF 属性下
        MENU_CONF: {},
      },
    };
  },
  methods: {
    onCreated(editor) {
      this.editor = Object.seal(editor); // 【注意】一定要用 Object.seal() 否则会报错
    },
    onChange(editor) {

        //改父组件的值
        this.$emit("input", editor.getHtml())
        console.log("onChange", editor.getHtml()); // onChange 时获取编辑器最新内容

    },
    getEditorText() {
      const editor = this.editor;
      if (editor == null) return;

      console.log(editor.getText()); // 执行 editor API
    },
    printEditorHtml() {
      const editor = this.editor;
      if (editor == null) return;

      console.log(editor.getHtml()); // 执行 editor API
    },
  },
  mounted() {
    // setTimeout(() => {
      // this.modelHtml = this.props.modelHtml;
      // initFinished = true;
    // }, 10);
  },
  beforeDestroy() {
    const editor = this.editor;
    if (editor == null) return;
    editor.destroy(); // 组件销毁时，及时销毁 editor ，重要！！！
  },
  watch:{
    modelHtml: function (_new, _old) {
      this.initFinished = true;
      console.log("Watch到了" + _new)
      this.modelHtml = _new
    }
  }
}
</script>

<style src="@wangeditor/editor/dist/css/style.css"></style>