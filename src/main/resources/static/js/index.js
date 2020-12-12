/**
 * 
 */
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

let marsRoverType = getUrlParameter("marsApiRoverData")
highlightButtonByRoverType(marsRoverType)

function highlightButtonByRoverType(roverType) {
	// if page loaded for the first time, set the default button and change the css class to primary
	if(marsRoverType == '') {
		marsRoverType = 'Opportunity'
	}
	
	document.getElementById('marsApi' + roverType).classList.remove('btn-secondary')
	document.getElementById('marsApi' + roverType).classList.add('btn-primary')
}