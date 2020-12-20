/**
 * 
 */


let userId = getUrlParameter('userId')

if(userId == null || userId == ''){
	console.log(userId)
	userId = localStorage.getItem('userId')
}

if(userId != null && userId != '') {
	console.log(userId)
	localStorage.setItem('userId', userId)
	document.getElementById('userId').value = userId
}

// get all the buttons prepended with 'marsApi'
let marsApiButtons = document.querySelectorAll("button[id*='marsApi']")

marsApiButtons.forEach(button => button.addEventListener('click', function () {
	const buttonId = this.id
	const roverId = buttonId.split('marsApi')[1]
	let apiData = document.getElementById('marsApiRoverData')
	apiData.value = roverId
	document.getElementById('formRoverType').submit()
}))

// @ https://davidwalsh.name/query-string-javascript
function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};

let marsRoverType = document.getElementById('marsApiRoverData').value
highlightButtonByRoverType(marsRoverType)

// sets the input field back to the last number before hitting the submit button instead of fall back to default 1

let marsSol = document.getElementById('marsSol').value
if(marsSol != null && marsSol != '' && marsSol >= 0) {
	document.getElementById('marsSol').value = marsSol
}


function highlightButtonByRoverType(roverType) {
	// if page loaded for the first time, set the default button and change the css class to primary
	if(roverType == '') {
		roverType = 'Opportunity'
	}
	
	document.getElementById('marsApi' + roverType).classList.remove('btn-secondary')
	document.getElementById('marsApi' + roverType).classList.add('btn-primary')
}