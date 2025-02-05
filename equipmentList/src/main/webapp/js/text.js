function update(obj , data) {
	//重置
	resets();
	//获取
	var name1 = document.getElementById("name1");
	var name2 = document.getElementById("name2");
	var code = document.getElementById("code");
	var select = document.getElementById("select");
	var type1 = document.getElementById("type1");
	var time = document.getElementById("time");
	var type2 = document.getElementById("type2");
	
	//获取tr和td对，以及选中的下标
	var tr = obj.parentNode.parentNode;
	var td = tr.getElementsByTagName('td');
	rowIndex = obj.parentNode.parentNode.rowIndex;
	
	//赋值
	name1.value = td[0].innerHTML;
	name2.value = td[1].innerHTML;
	code.value = td[2].innerHTML;
	select.value = td[3].innerHTML;
	type1.value = td[4].innerHTML;
	time.value = td[5].innerHTML;
	type2.value = td[6].innerHTML;

       // 获取表单
      var form = document.getElementById("dataForm");

      // 创建新的表单行（div元素）
      var row = document.createElement("div");

      // 创建新的表单元素（input元素）
      var input = document.createElement("input");
      input.type = "hidden";
      input.name = "data";
      input.value = data;

      // 将新的表单元素添加到表单行中
      row.appendChild(input);

      // 将新的表单行添加到表单中
      form.appendChild(row);

	var updateButton = document.getElementById('ft');
	updateButton.setAttribute('value', '确定');
	document.getElementById('dataForm').action = "edit";
}

//修改
function save() {
	//获取

	var table = document.getElementById('tab1');
	var name1 = document.getElementById("name1");
	var name2 = document.getElementById("name2");
	var code = document.getElementById("code");
	var select = document.getElementById("select");
	var type1 = document.getElementById("type1");
	var time = document.getElementById("time");
	var type2 = document.getElementById("type2");


	//赋值
	table.rows[rowIndex].cells[0].innerHTML = name1.value;
	table.rows[rowIndex].cells[1].innerHTML = name2.value;
	table.rows[rowIndex].cells[2].innerHTML = code.value;
	table.rows[rowIndex].cells[3].innerHTML = select.value;
	table.rows[rowIndex].cells[4].innerHTML = type1.value;
	table.rows[rowIndex].cells[5].innerHTML = time.value;
	table.rows[rowIndex].cells[6].innerHTML = type2.value;

	resets();

	//更新成功后，将按钮改回原来按钮，同时重置
//	var updateButton = document.getElementById('update');
//	updateButton.setAttribute('value', '新增');
//	updateButton.setAttribute('type', 'button');
//	updateButton.setAttribute('id', 'ft');
//	updateButton.setAttribute('onclick', 'add()');

}



//重置
function resets() {
	var inputs = document.getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == 'text') {
			inputs[i].value = "";
		}
		if (inputs[i].id == 'update') {
			var updateButton = document.getElementById('update');
			updateButton.setAttribute('value', '新增');
			updateButton.setAttribute('type', 'button');
			updateButton.setAttribute('id', 'ft');
			updateButton.setAttribute('onclick', 'add()');
		}
	}
	//重置下拉框
	var place = document.getElementById('name1');
	place.selectedIndex = 0;
	var place = document.getElementById('select');
	place.selectedIndex = 0;
	var place1 = document.getElementById('type1');
	place1.selectedIndex = 0;
	var place2 = document.getElementById('type2');
	place2.selectedIndex = 0;

}

//判断一个字符串是否为合法的日期格式：yyyy-MM-dd HH:mm:ss
function check(ds) {
	var parts;
	parts = ds.split(' ');
	if (parts.length == 2) {
		if (isDatePart(parts[0]) == true && isTimePart(parts[1]) == true) {
			return true;
		}
	}
	return false;
}
//判断一个字符串是否为合法的日期格式：yyyy-MM-dd
function isDatePart(dateStr) {
	var parts;

	parts = dateStr.split('-');

	if (parts.length == 3) {
		//日期部分不允许缺少年、月、日中的任何一项
		for (i = 0; i < 3; i++) {
			//如果构成日期的某个部分不是数字，则返回false
			if (isNaN(parts[i])) {
				return false;
			}
		}

		y = parts[0]; //年
		m = parts[1]; //月
		d = parts[2]; //日

		if (m == 01 || m == 03 || m == 05 || m == 07 || m == 08 || m == 10 || m == 12) {
			if ((d <= 31 && d >= 10) || d == 01 || d == 02 || d == 03 || d == 04 || d == 05 || d == 06 || d == 07 ||
				d == 08 || d == 09) {
				return true;
			}
		} else if (m == 04 || m == 06 || m == 09 || m == 11) {
			if ((d <= 30 && d >= 10) || d == 01 || d == 02 || d == 03 || d == 04 || d == 05 || d == 06 || d == 07 ||
				d == 08 || d == 09) {
				return true;
			}
		} else if (m == 02) {
			if ((y % 100 != 0 && y % 4 == 0) || (y % 100 == 0 && y % 400 == 0)) {
				if ((d <= 29 && d >= 10) || d == 01 || d == 02 || d == 03 || d == 04 || d == 05 || d == 06 || d == 07 ||
					d == 08 || d == 09) {
					return true;
				}
			} else {
				if ((d <= 28 && d >= 10) || d == 01 || d == 02 || d == 03 || d == 04 || d == 05 || d == 06 || d == 07 ||
					d == 08 || d == 09) {
					return true;
				}
			}
		}
	}
	return false;
}

//判断一个字符串是否为合法的时间格式：HH:mm:ss
function isTimePart(timeStr) {
	var parts;

	parts = timeStr.split(':');

	if (parts.length == 3) {
		//日期部分不允许缺少小时、分钟、秒中的任何一项

		for (i = 0; i < parts.length; i++) {
			//如果构成时间的某个部分不是数字，则返回false
			if (isNaN(parts[i])) {
				return false;
			}
		}

		h = parts[0]; //小时
		m = parts[1]; //分钟
		s = parts[2]; //秒

		if ((h == 00 || h == 01 || h == 02 || h == 03 || h == 04 || h == 05 || h == 06 || h == 07 || h == 08 || h ==
				09 || (h >= 10 && h <= 23))) {
			//限制小时的范围
			if ((m == 00 || m == 01 || m == 02 || m == 03 || m == 04 || m == 05 || m == 06 || m == 07 || m == 08 || m ==
					09 || (m >= 10 && m <= 59))) {
				//限制分钟的范围	
				if ((s == 00 || s == 01 || s == 02 || s == 03 || s == 04 || s == 05 || s == 06 || s == 07 || s == 08 ||
						s == 09 || (s >= 10 && s <= 59))) {
					//限制秒的范围
					return true;
				}
			}
		}
	}

	return false;
}
