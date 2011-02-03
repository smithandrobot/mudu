
$(document).ready(function() {
	var c = new Controller();
});


function Controller()
{
	allTimeTab = new TabDecorator($('#allTimeLeadersTab'));
	allTimeTab.id = "allTimeTab";

	
	thisWeeksTab = new TabDecorator($('#thisWeeksLeadersTab'));
	thisWeeksTab.id = "thisWeeksTab";
	thisWeeksTab.selected = false;
	
	
	allTimeTab.select();
	
	function tabSelected(tab)
	{
	// if(tab.id == "allTimeTab") thisWeeksTab.deslect();
	// if(tab.id == "thisWeeksTab") allTimeTab.deslect();
	}
}

function tabSelected(tab)
{
	//alert(tab.id+" thisWeeksTab: "+thisWeeksTab)
	if(tab.id == "allTimeTab") thisWeeksTab.deselect();
	if(tab.id == "thisWeeksTab") allTimeTab.deselect();
}


function TabDecorator(e)
{
	var element 	= e;
	var self 		= this;
	
	this.id 		= null;
	this.selected 	= false;
	this.select     = select;
	this.deselect 	= deselect;
	
	element.css({backgroundPosition: '0px 10px'});

	element.bind('click', click);
	element.bind('mouseover', hover);
	element.bind('mouseout', mouseOut);

	
	function select()
	{
		self.selected = true;
		element.css('background-position', '0px 0px');
		element.css('height', '100px');
		tabSelected(self);
	}
	
	
	function deselect()
	{
		self.selected = false;
		element.css('background-position', '0px 10px');
		element.css('height', '76px');
	}
	
	
	function click(event)
	{
		if(self.selected) return;
		select()
	}
	
	function hover(event)
	{
		//element.css('background-position', '0px 0px');
		if(!self.selected) element.animate({backgroundPosition: '0px 0px'}, 1000);
	}
	
	function mouseOut(event)
	{
		if(!self.selected) 	element.css('background-position', '0px 10px');

	}
	
	this.toString = function(){ return "TabDecorator - "+this.id;};
	return this;
}