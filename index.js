// js实现菜单折叠
var flag=true; //开关按钮
function show_menu(){
	var menu1 = document.getElementById("menu1");
	if(flag){
		menu1.style.display="block";
		flag = false;
	}else{
		menu1.style.display="none";
		flag = true;
	}	
}

function show_menu1(){
	var menu1 = document.getElementById("menu1");
	menu1.style.display="none";
	flag = true; //鼠标离开时将flag默认回true
}
