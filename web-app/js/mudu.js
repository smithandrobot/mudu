


// Leaderboard
$(document).ready(function() {
	var c = new Controller();
	$("tr").click(function(){
	  	if($(this).find("a").attr("href"))
		{
            window.open($(this).find("a").attr("href"), 'fbprofile'); return false;
		}
		
	});
	
	var startTab = top.location.hash;
});


function Controller()
{
	allTimeTab = new TabDecorator($('#allTimeLeadersTab'));
	allTimeTab.id = "allTimeTab";


	thisWeeksTab = new TabDecorator($('#thisWeeksLeadersTab'));
	thisWeeksTab.id = "thisWeeksTab";
	thisWeeksTab.selected = false;


	allTimeTab.select();
    var f = new FlashEmbedder();
	f.embedFash();
}

function tabSelected(tab)
{
	//alert(tab.id+" thisWeeksTab: "+thisWeeksTab)
	if(tab.id == "allTimeTab") thisWeeksTab.deselect();
	if(tab.id == "thisWeeksTab") allTimeTab.deselect();

	if(tab.id == "allTimeTab") $('#tables-container').animate({'margin-left':'0px'})
	if(tab.id == "thisWeeksTab") $('#tables-container').animate({'margin-left':'-518px'})
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
		var index = (self.id == "allTimeTab") ? 1000 : 1010;
		element.css('z-index', index);
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
		if(self.selected) return false;
		select();
		top.location.hash = self.id;
		return false;
	}

	function hover(event)
	{
		if(!self.selected) element.css('background-position', '0px 0px');
	}

	function mouseOut(event)
	{
		if(!self.selected) 	element.css('background-position', '0px 10px');

	}

	this.toString = function(){ return "TabDecorator - "+this.id;};
	return this;
}


function FlashEmbedder()
{
	var swfCount = 0;
	var flashvars = {};
	var params = {};
	var attributes = {};
	
	params.wmode 	= "transparent";
	this.embedFash 	= embedFlash;
	
	function embedFlash()
	{
		$('.dirty_hairy').each( function(){ embedCharacter('mudpie', $(this)); } );
		$('.sasplotch').each( function(){ embedCharacter('sasplotch', $(this)); } );
		$('.buck_wild').each( function(){ embedCharacter('buckwild', $(this)); } );
		$('.dr_walnuts').each( function(){ embedCharacter('walnuts', $(this)); } );
	}
	
	
	function embedCharacter( type , element )
	{
		++swfCount;
		var ac  	 	 	= 'col-creature'; // getClass( type );
		var file 		 	= getFile( type );
		var id			 	= 'creature-'+swfCount;
		var a 			 	= {};
		//attributes['class']	= ac;
		element.attr('id', id);
		swfobject.embedSWF("swf/"+file, id, "50", "50", "10", false, flashvars, params, attributes);
	}

	
	function getFile( type ) 
	{
		if(type == 'mudpie') return 'leaderboard_mudpie.swf';
		if(type == 'sasplotch') return 'leaderboard_sasplotch.swf';
		if(type == 'buckwild') return 'leaderboard_buckwild.swf';
		if(type == 'walnuts') return 'leaderboard_walnuts.swf';
	}
}







