<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<link href="<%=request.getContextPath() %>/resources/fancytree/dist/skin-win8/ui.fancytree.css" rel="stylesheet">
<script type="text/javascript" src = "<%=request.getContextPath() %>/resources/jquery-ui-1.12.1/jquery-ui.js"></script>
<script src="<%=request.getContextPath() %>/resources/fancytree/dist/jquery.fancytree-all.min.js"></script>

<script src="<%=request.getContextPath() %>/resources/fancytree/dist/modules/jquery.fancytree.js"></script>

</head>
<body>
<div id="tree">
    <ul id="treeData" style="display: none;">

      <li id="file01" class="folder">01
        <ul>
          <li>first.jsp
          <li>gugudan.tmpl
          <li>sample.tmpl
        </ul>
        
        
       <li id="file02" class="folder">02
        <ul>
          <li>calendar.jsp
          <li>gugudan.jsp
          <li>standard.jsp
        </ul>
        
        
        <li id="file03" class="folder">03
        <ul>
          <li>parameterProcess.jsp
          <li>requestDesc.jsp
          <li>resourceIdentify.jsp
        </ul> 
        
    </ul>
  </div>

  <script type="text/javascript">

    $(function(){

      // using default options

     $("#tree").fancytree({
autoActivate: false, // we use scheduleAction()
autoCollapse: true,
// autoFocus: true,
autoScroll: true,
clickFolderMode: 0, // expand with single click 원래는 3이었음.
minExpandLevel: 1, //폴더를 오픈할것인지 말것인지
tabindex: "-1", // we don't want the focus frame
// toggleEffect: { effect: "blind", options: {direction: "vertical", scale: "box"}, duration: 2000 },
// scrollParent: null, // use $container
tooltip: function(event, data) {
return data.node.title;
},
focus: function(event, data) {
var node = data.node;
// Auto-activate focused node after 1 second
if(node.data.href){
node.scheduleAction("activate", 1000);
}
},
blur: function(event, data) {
data.node.scheduleAction("cancel");
},
beforeActivate: function(event, data){
var node = data.node;

if( node.data.href && node.data.target === "_blank") {
window.open(node.data.href, "_blank");
return false; // don't activate
}
},
activate: function(event, data){
var node = data.node,
orgEvent = data.originalEvent || {};

// Open href (force new window if Ctrl is pressed)
if(node.data.href){
window.open(node.data.href, (orgEvent.ctrlKey || orgEvent.metaKey) ? "_blank" : node.data.target);
}
// When an external link was clicked, we don't want the node to become
// active. Also the URL fragment should not be changed
if( node.data.target === "_blank") {
return false;
}
// Append #HREF to URL without actually loading content
// (We check for this value on page load re-activate the node.)
if( window.parent &&  parent.history && parent.history.pushState ) {
parent.history.pushState({title: node.title}, "", "#" + (node.data.href || ""));
}
},
click: function(event, data){
// We implement this in the `click` event, because `activate` is not
// triggered if the node already was active.
// We want to allow re-loads by clicking again.
var node = data.node,
orgEvent = data.originalEvent;

// Open href (force new window if Ctrl is pressed)
if(node.isActive() && node.data.href){
window.open(node.data.href, (orgEvent.ctrlKey || orgEvent.metaKey) ? "_blank" : node.data.target);
}
}
});
// On page load, activate node if node.data.href matches the url#href
var tree = $(":ui-fancytree").fancytree("getTree"),
frameHash = window.parent && window.parent.location.hash;

if( frameHash ) {
frameHash = frameHash.replace("#", "");
tree.visit(function(n) {
if( n.data.href && n.data.href === frameHash ) {
n.setActive();
return false; // done: break traversal
}
});
}
      
      
      

    });

  </script>



</body>
</html>