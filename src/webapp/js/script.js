var arrayXC = JSON.parse(`[
	{
		"name":"name_1",
		"season_count":4,
		"rating":4.7
	},
	{
		"name":"name_2",
		"season_count":5,
		"rating":5.0
	}
]`)


var i =0;
function Serial(id, name, season_count, rating, descriprion) {
	this.id = id;
	this.name = name;
	this.season_count = season_count;
	this.rating = rating;
	this.descriprion = descriprion;
}
var serials = new Array();

var mainContainerView = document.querySelector(".searche-item-list");

for (var index of arrayXC) {
	addSerial(arrayXC[i].name, arrayXC[i].season_count, arrayXC[i].rating);
}

function addNewSerial() {
	var name = document.getElementById("inputNameId").value;
	var season_count = document.getElementById("inputSeasonCountId").value;
	var rating = document.getElementById("inputRatingId").value;
	if (name.length > 0 && season_count.length > 0 && rating.length > 0) {
		addSerial(name, season_count, rating);

		document.getElementById("inputNameId").value = "";
		document.getElementById("inputSeasonCountId").value = "";
		document.getElementById("inputRatingId").value = "";
	}
}

function addSerial(name, season_count, rating) {
	var newSerial = new Serial(i++, name, season_count, rating);
	serials.push(newSerial);
	createView(newSerial);
}

function createView(serial) {

	var containerView = document.createElement("div");
	var nameView = document.createElement("h3");
	var seasonCountView = document.createElement("h4");
	var ratingView = document.createElement("h4");

	var nameText = document.createTextNode("Name: " + serial.name);
	var seasonCountText = document.createTextNode("Season count: " + serial.season_count);
	var ratingText = document.createTextNode("Rating: " + serial.rating);

	nameView.appendChild(nameText);
	seasonCountView.appendChild(seasonCountText);
	ratingView.appendChild(ratingText);

	containerView.appendChild(nameView);
	containerView.appendChild(seasonCountView);
	containerView.appendChild(ratingView);

	containerView.setAttribute("class", "serial card");
	containerView.setAttribute("id", serial.id);
	containerView.setAttribute("onclick", "onClickItem("+serial.id+")");

	mainContainerView.appendChild(containerView);
}

function searchSerial() {
	var searcheName = document.getElementById("testasdasd").value;

	var newList = new Array();

	for (var index in serials) {
		if (serials[index].name.includes(searcheName)) {
			newList.push(serials[index]);
		}
	}

	updateList(newList)
}

function updateList(newList) {
	while (mainContainerView.firstChild) {
		mainContainerView.removeChild(mainContainerView.firstChild);
	}

	for (var index in newList) {
		createView(newList[index]);
	}
}

function onClickItem(itemId) {
	clearContentView();
	var item = getById(itemId);

	var nameViewContent = document.getElementById("serialNameContentId");
	var seasonCountAndRatingViewContent = document.getElementById("serialSeasonCountAndRatingContentId");

	nameViewContent.appendChild(document.createTextNode(item.name));

	seasonCountAndRatingViewContent.appendChild(document.createTextNode(`Season count: ${item.season_count}`));
	seasonCountAndRatingViewContent.appendChild(document.createElement("br"));
	seasonCountAndRatingViewContent.appendChild(document.createTextNode(`Rating: ${item.rating}`));

	document.getElementById("serialDescriprionContentId").value = item.descriprion;

	document.getElementById("saveContentButtonId").value = item.id;
	document.getElementById("deleteContentButtonId").value = item.id;
}

function getById(id) {
	for (var index in serials) {
		if (serials[index].id == id) {
			return serials[index];
		}
	}
}

function onClickSaveItem() {
	var itemId = document.getElementById("saveContentButtonId").value;
	var descriptionText = document.getElementById("serialDescriprionContentId").value;
	for (var index in serials) {
		if (serials[index].id == itemId) {
			serials[index].descriprion = descriptionText;
			break;
		}
	}

	clearContentView();
}

function onClickRemoveItem() {
	var itemId = document.getElementById("deleteContentButtonId").value;
	for (var index in serials) {
		if (serials[index].id == itemId) {
			mainContainerView.removeChild(document.getElementById(itemId));
			serials.splice(index, 1);
			break;
		}
	}

	clearContentView();
}

function clearContentView() {
	var nameViewContent = document.getElementById("serialNameContentId");
	while (nameViewContent.firstChild) {
		nameViewContent.removeChild(nameViewContent.firstChild);
	}

	var seasonCountAndRatingViewContent = document.getElementById("serialSeasonCountAndRatingContentId");
	while (seasonCountAndRatingViewContent.firstChild) {
		seasonCountAndRatingViewContent.removeChild(seasonCountAndRatingViewContent.firstChild);
	}

	document.getElementById("serialDescriprionContentId").value = "";

	document.getElementById("saveContentButtonId").value = "";
	document.getElementById("deleteContentButtonId").value = "";
}