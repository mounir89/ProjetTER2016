

/*console.log(croi(500,660));
{
    x: parseFloat(items[10]),
    color: getColorbyReliability(parseFloat(items[12])),
    y: parseFloat(items[11]),
    numDoc : parseFloat(items[1]),
    Topic:items[0],
    Glucose_min: parseFloat(items[6]),
    Glucose_max: parseFloat(items[7]),
    E_factore_Min: parseFloat(items[8]),
    E_factore_Max: parseFloat(items[9]) 
}
*/
//console.log("ikbal");
Highcharts.Renderer.prototype.symbols.vline = function(x, y, width, height) {
    console.log(E_factore_Min);
    return ['M',x ,y + width / 2,'L',x+height,y + width / 2];
};
/*Highcharts.Renderer.prototype.symbols.hline = function(x, y, width, height, obj1, obj2) {
   // return ['M',10,y + height / 2,'L',x,y + width / 2];
  //  console.log(obj1);
    //console.log(height);
    
        

    return ['M',x ,y + width / 2,'L',x+height,y + width / 2];
};*/

function getColorbyReliability(reliability, minvalue, maxvalue){
    var colorResult = "";
    minvalue = (minvalue == undefined )? 0: minvalue;
    maxvalue = (maxvalue == undefined )? 1: maxvalue;
    
    var interval = (maxvalue - minvalue)/10;
    if(reliability < minvalue +(1 * interval)){
        colorResult = '#F3F781';
    }
    else if(reliability < minvalue +(2 * interval)){
        
         colorResult = '#F4FA58';
    }
    else if(reliability < minvalue +(3 * interval)){
       
         colorResult = '#FFFF00';
    
    }
    else if(reliability < minvalue +(4 * interval)){
         
        
       colorResult =' #DF7401';
    }
     else if(reliability < minvalue +(5 * interval)){
        colorResult = '#80FF00';
        
    }
     else if(reliability < minvalue +(6 * interval)){
        colorResult = '#00FF00';
        
    }
     else if(reliability < minvalue +(7 * interval)){
        colorResult = '#04B404';
        
    }
     else if(reliability < minvalue +(8 * interval)){
        colorResult = '#0B610B';
        
    }
     else if(reliability < minvalue +(9 * interval)){
        colorResult = '#0A2A0A)';
        
    }
    
        else {
        colorResult = '#0B1907';
    }
    
    
    return colorResult;
}
function calculerInterval(max, min)
{
    var valeurIntervale = (max - min);
    
    return valeurIntervale;
}

function getColorInterieurPoint(reliability, minvalue, maxvalue){
    var colorResult = "";
    minvalue = (minvalue == undefined )? 0: minvalue;
    maxvalue = (maxvalue == undefined )? 1: maxvalue;
    
    var interval = (maxvalue - minvalue)/3;
    if(reliability < minvalue +(1 * interval)){
       colorResult = '#FFFF00';
    }
    else if(reliability < minvalue +(2 * interval)){
      colorResult = '#04B404';
    }
  
    else {
        colorResult = '#0B1907';
    }
    
    
    return colorResult;
}


    function showgraphVisualisation(){
    
    var options = {
        chart: {
            //ignoreHiddenSeries: false,
            renderTo: 'container',
            type: 'scatter',
            zoomType: 'xy',
            borderColor: '#660000',
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#fff ',
            borderWidth: 2,
            height: 500,
            width: 800,
            borderRadius: 20,
            ignoreHiddenSeries: true
        },
        title: {
            text: 'Best Experience',
               style: {
                   color: '#FF0080',
                   fontWeight: 'bold',
                   fontSize: "20px"

                }
        },
        xAxis: {
            max: 100,
            title: {
                text: 'E-Factor ',
             style: {
                   color: '#00080',
                   fontWeight: 'bold',
                   fontSize: "20px"

                }
            },
            categories: []
        },
        yAxis: {
            title: {
                text: 'Glucose Yields ',
                  style: {
                   color: '#000000',
                   fontWeight: 'bold',
                   fontSize: "20px"

                }
            },
                        //tickInterval: ,

            categories1: []

        },

        legend: {
       /* layout: 'vertical',
        align: 'center',
        verticalAlign: 'bottom',
        x: 50,
        y: 20,
       // floating: true,
        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF',
        borderWidth: 1,
            //borderRadius: 50,
            padding: 5,
            
            Height: 500,
            
          //   align: 'center',
1
        margin: 0,
       // verticalAlign: 'bottom',
        //y: 50,
        symbolHeight: 20*/
          title: {
         text:'Biorefinery process' +'\uf062'+ ' index de fiabilite',
          style: {  

                   color: '#FF0040',
                   fontWeight: 'bold',
                   fontSize: "15px"

                }
     },
            x:50,
            margin:20,
               width: 500,
            //floating: true,
            align: 'left',
            x: 70, // = marginLeft - default spacingLeft
            itemWidth: 120,
            borderWidth: 0
    },

     
        navigation: {
                 buttonOptions: {
                height: 40,
                width: 48,
                symbolSize: 24,
                symbolX: 23,
                symbolY: 21,
                symbolStrokeWidth: 2,
                  theme: {
                    'stroke-width': 4,
                  //  stroke: '#660000',
                            fill: '#bada55',
                    width:1000,
                    r: 2,

                    states: {
                        hover: {
                            fill: '#bada55'
                        },
                        select: {
                            stroke: '#660000',
                            fill: 'pink'
                        }
                    }
                }},
            
            menuItemStyle: {
                padding: '05px',
             
                color: '#660000',
                fontSize:60

            },

            menuItemHoverStyle: {
              background: 'pink',
             color: '#660000'
            }
        },
    
    series: []
    };
    options.plotOptions = {
        
        series: {
            events: {
                legendItemClick: function(event) {
                  // console.log(event);
                    //alert(this.name);
                    
                    var evenementselectionne = this.name.trim();
                    alert(evenementselectionne);
                    var topic="pm"
                 if (evenementselectionne==topic)
                 {
                     var  serie= options.series[4];
                 //    serie.hide();
                        Modificationserie(serie[4]);
                     //  console.log("ikbal______");
                    // options.series[4].ignoreHiddenSeries();
                       console.log(options.series[4]);
            
                    //console.log(options.chart);//.hide();
                    //options.series[4].data.length = 0;
                    // options.series[4].marker.radius = 0;
                    //  console.log(options.series[4]);
                    //  console.log(options.series[4].color);
                 //  console.log(options.series[4].data);
                     //chart.series[5].hide();
                          
                 }
               //console.log(options.series[5].;
                      return false;
                            
                }
            },
                point: {
                    
                    events: {
                        click: function () {
                         //location.href = this.options.url;
                            console.log(this.numDoc);
                            $.get( "testenvoie.php", { numerodocument: this.numDoc},
                                function( data ) {
                                    if(!data.erreur){
                                        alert(data.lien);
                                        location.href = data.lien;
                                    }
                                    console.log(data);

                                },  
                                "json"
                            );

                        },
                           remove: function () {
                            if (!confirm('Do you really want to remove the first point?')) {
                                return false;
                            }
                           }
                    }
                }
        },

        scatter: {
            marker: {
                radius: 10,
                states: {
                    hover: {
                        enabled: false,
                        lineColor: 'rgb(100,100,00)'
                    }
                }
            },
            states: {
                hover: {
                    marker: {
                        enabled: false,


                    }
                }
            }
     
            
        }
              /*,
         series: {
                pointStart: 145,
                pointInterval: 24 * 3600 * 1000 // one day
            }*/
    }
        //console.log(tooltip.houssam);
    /*
     Load the data from the CSV file. This is the contents of the file:

        Apples,Pears,Oranges,Bananas,Plums
        John,8,4,6,5
        Jane,3,4,2,3
        Joe,86,76,79,77
        Janet,3,16,13,15

     */ 
     // get est pour obtenir les données 
    $.get('fileBestExResultGraphic.csv', function(data) {
    var numberoflines = data.split('\n').length;
    // Split the lines
    var lines = data.split('\n');
        
        
        Highcharts.Renderer.prototype.symbols.hline= function(x, y, width, height, obj1, obj2) {
           return ['M',x ,y + width / 2,'L',x+height,y + width / 2];
};

        
        
        
        
    var datapm = {
        name:" pm",
      color:'#01A9DB',
        data: [],
        marker: {
                symbol: 'triangle',
             
                  radius: 10
            
            }
        
    }
    var datapmfiabilite =  {
        name:" pm_fiabilite",
        color: 'rgba(0, 245, 30, 1)',
        data: [],
        datapmteste:[],
         showInLegend: false,
        marker: {
                symbol: 'triangle',
                  radius: 15
            
            }
    }


    var datapm_ufm = {
        name:" pm_ufm",
       color: '#01A9DB',
        data: [],
        marker: {
                symbol: 'diamond',
                  radius: 10
            
            }
        }
      
      var datapmufmfiabilite =  {
        name:" pm",
       // color: 'rgba(130, 0, 51, 1)',
        data: [],
         showInLegend: false,
        marker: {
                symbol: 'diamond',
                  radius: 15
            
            }
    }
    var datapm_pc_ufm_ps = {
        name:" pm_pc_ufm_ps",
        color:'#01A9DB',
        data: [],
         marker: {
                symbol: 'square',
                  radius: 10
            
            }
    }
      var datapmufmpsfiabilite =  {
        name:" pm_pc_ufm_ps",
          id: "ikbal",
        //color: 'rgba(130, 0, 51, 1)',
        data: [],
         showInLegend: false,
        marker: {
                symbol: 'square',
                  radius: 15
            
            }
    }
   

    var datapm_pc_ps = {
        
        name:" pm-pc-ps",
       color: '#01A9DB',
        data: [],
         marker: {
                symbol: 'circle',
                  radius: 10
            
            }
    }
/*    var line =}*/
    
    var datalinepmx= {
        name:" pm-x",
        color:'01A9DB',
         showInLegend: false,

        data: [],
        marker:{
        symbol:'hline',
           // radius:35,
            lineColor:'#c00',
            lineWidth:1
        }
        
    
    }
        var datalinepmufmx= {
        name:" pm-ufm",
        color:'rgba(0,0,0, 1)',
         showInLegend: false,

        data: [],
        marker:{
        symbol:'hline',
           // radius:35,
            lineColor:'#c00',
            lineWidth:1
        }
        
    
    }
         var datalinepmpcpsx= {
                name:" pm-pc-ps",
                color:'rgba(0,0,0, 1)',
                 showInLegend: false,

                data: [],
                marker:{
                symbol:'hline',
                   // radius:35,
                    lineColor:'#c00',
                    lineWidth:1
                }
        
    
    }
            
        var datalinepmpcufmpsx= {
                name:" pm-pc-ps",
                color:'rgba(0,0,0, 1)',
                 showInLegend: false,

                data: [],
                marker:{
                symbol:'hline',
                   // radius:35,
                    lineColor:'#c00',
                    lineWidth:1
                }
        
    
    }
        var datalineV = {
                name:" pm-pc-ps",
                color:'rgba(0,0,0, 1)',
                showInLegend: false,

                data: [],
                marker:{
                symbol:'vline',
                   // radius:35,
                    lineColor:'#c00',
                    lineWidth:1
                }
        }
      var couleur01 = 
                {
                    name:" 0.1",
                    data: [],
                    color: '#F3F781',
                    marker :{
                        symbol :'circle',
                        radius:7
                        
                        }
        }
        var couleur02 = 
                {
                    name:" 0.2",
                    data: [],
                    color:'#F4FA58',
                    marker :{
                        symbol :'circle',
                        radius:7
                        }
        }
          var couleur03 = 
                {
                    name:" 0.3",
                    data: [],
                    color: '#FFFF00',
                    marker :{
                        symbol :'circle',
                        radius:7
                        }
        }
            var couleur04 = 
                {
                    name:" 0.4",
                    data: [],
                    color: '#DF7401',
                    marker :{
                        symbol :'circle',
                        radius:7
                        }
        }
              var couleur05 = 
                {
                    name:" 0.5",
                    data: [],
                    color:  '#80FF00',
                    marker :{
                        symbol :'circle',
                        radius:7
                        }
        }
                var couleur06 = 
                {
                    name:" 0.6",
                    data: [],
                    color: '#00FF00',
                    marker :{
                        symbol :'circle',
                        radius:7
                        }
        }
                  var couleur07 = 
                {
                    name:" 0.7",
                    data: [],
                    color: '#04B404',
                    marker :{
                        symbol :'circle',
                        radius:7
                        }
        }
                    var couleur08 = 
                {
                    name:" 0.8",
                    data: [],
                    color: '#0B610B',
                    marker :{
                        symbol :'circle',
                        radius:7
                        }
        }
                      var couleur09 = 
                {
                    name:" 0.9",
                    data: [],
                    color: '#0A2A0A',
                    marker :{
                        symbol :'circle',
                        radius:7
                        }
        }
                        var couleur010 = 
                {
                    name:" 1",
                    data: [],
                    color: '#0B1907',
                    marker :{
                        symbol :'circle',
                        radius:7
                        }
        }
    var  dataline =
        {
            data :[]
        }
            var datapmpcpsfiabilite =  {
        name:" pm",
     //   color: 'rgba(130, 0, 51, 1)',
        data: [],
          datapm_pc_ps_fiabilite: [],
         showInLegend: false,
        marker: {
                symbol: 'circle',
                  radius: 15
            
            }
    }
    $.each(lines, function(lineNo, line) { 
        line = line.replace(/['"]+/g, '');
        var items = line.split(',');
        // header line containes categories
        if (lineNo == 0) {
           // veut dire l'entete du fichier csv (topic, gulcose yield ...)
            $.each(items, function(itemNo, item) {
                if (itemNo == 10 ) options.xAxis.categories.push(item); //l'ajout de l'entete laxe x
            });
            $.each(items, function(itemNo, item) {
                //  console.log(item)

                if (itemNo == 11 ) options.yAxis.categories1.push(item); //l'ajout de l'entete laxe x
            });
        }
        // the rest of the lines contain data with their name in the first position
        else {
            switch (items[0])
            {
                 case "pm":
                   datapm.data.push(
                         {
                            
                       
                            color: getColorInterieurPoint(parseFloat(items[10])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13]) 
                        }
                    );
                  datalinepmx.data.push( 
                        {
                            x: parseFloat(items[10]),
                            color: getColorbyReliability(parseFloat(items[12])),
                            y: parseFloat(items[11]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min: parseFloat(items[6]),
                            Glucose_max: parseFloat(items[7]),
                            E_factore_Min: parseFloat(items[8]),
                            E_factore_Max: parseFloat(items[9]),
                            
                            
                            

                            
                            marker: { fillColor: '#BF0B23', radius: calculerInterval( parseFloat(items[9]), parseFloat(items[8]))*8}
                        }
                    );
                    
                 
                       datalineV.data.push( 
                        {
                            x: parseFloat(items[10]),
                            color: getColorbyReliability(parseFloat(items[12])),
                            y: parseFloat(items[11]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min: parseFloat(items[6]),
                            Glucose_max: parseFloat(items[7]),
                            E_factore_Min: parseFloat(items[8]),
                            E_factore_Max: parseFloat(items[9]),
                            marker: { fillColor: '#BF0B23', radius: calculerInterval( parseFloat(items[6]), parseFloat(items[7]))}
                        }
                    );
                    datapmfiabilite.data.push( 
                        {
                            color: getColorbyReliability(parseFloat(items[9])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13])
                        }
                    );
                 //datapmfiabilite.datapmteste.push(data);  //integration d'un  objet  dans un autre  ne marche pas  je dois  chercher autre  solution
                  //  options.tooltip.valueSuffix.push([parsefloat(items[1])]);
                    break;
                case "pm_ufm":
                    datapm_ufm.data.push(
                        {
                            
                            color: getColorInterieurPoint(parseFloat(items[10])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13])
                        }
                    );
                      datapmufmfiabilite.data.push( 
                        {
                            color: getColorbyReliability(parseFloat(items[9])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13])
                        }
                    );
                         datalinepmufmx.data.push( 
                        {
                            // color: getColorInterieurPoint(parseFloat(items[10])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13]) ,
                            
                            marker: { fillColor: '#BF0B23', radius: calculerInterval( parseFloat(items[9]), parseFloat(items[8])) *8 }
                        }
                    );
                  
                    
                    break;
                    case "pm_pc_ufm_ps":
                    datapm_pc_ufm_ps.data.push(
                             {
                            
                            color: getColorInterieurPoint(parseFloat(items[10])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13]) 
                            
                        }
                    );
                        datalinepmpcufmpsx.data.push( 
                        {
                           // color: getColorInterieurPoint(parseFloat(items[10])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13]) ,
                            marker: { fillColor: '#BF0B23', radius: calculerInterval( parseFloat(items[9]), parseFloat(items[8])) *8 }
                        }
                    );
                   /* dataline.data.push(
                        {
                    var rend    $(function () {
    renderer = new Highcharts.Renderer(
        $('#container')[0],
        400,
        300
        
    );
                             x: function(chart) { // on complete
     
        chart.renderer.path(['M', 15, 10, 'V', 15, 0])
            .attr({
                'stroke-width': 20,
                stroke: 'red'
            })
            .add();
                             },
                                 y:function(chart) { // on complete
     
        chart.renderer.path(['M', 6, 10, 'V', 15, 0])
            .attr({
                'stroke-width': 50,
                stroke: 'red'
            })
            .add();
                                 }
                        });*/
                       // [parseFloat(items[10]),parseFloat(items[11])]);
                   datapmufmpsfiabilite.data.push( 
                        {
                            color: getColorbyReliability(parseFloat(items[9])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13])

                        }
                    );
                    break;
                case "pm-pc-ps":
                   datapm_pc_ps.data.push(
                        {
                            
                            color: getColorInterieurPoint(parseFloat(items[10])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13])
                        }
                    );
                  datapmpcpsfiabilite.data.push( 
                        {
                            color: getColorbyReliability(parseFloat(items[9])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13])
                            
                        }
                    );
                        datalinepmpcpsx.data.push( 
                        {
                            // color: getColorInterieurPoint(parseFloat(items[10])),
                            x: parseFloat(items[14]),
                            y: parseFloat(items[15]),
                            numDoc : parseFloat(items[1]),
                            Topic:items[0],
                            Glucose_min : parseFloat(items[7]),
                            Glucose_max : parseFloat(items[8]),
                            E_factore_Min : parseFloat(items[12]),
                            E_factore_Max : parseFloat(items[13]),

                            marker: { fillColor: '#BF0B23', radius: calculerInterval( parseFloat(items[9]), parseFloat(items[8]))*8}
                        }
                    );
                    break;
            

        
         }

        }
    });
         
        
                options.series = [];
           
               // if(true || datalineV.data.length > 0 ) options.series.push(datalineV);
                /* Les  ligne  x */
               /* options.series.push(datalinepmx);
                options.series.push(datalinepmx);
                options.series.push(datalinepmufmx);
                options.series.push(datalinepmpcufmpsx);
                options.series.push(datalinepmpcpsx );
        
                /* Les  fiabilité   x */
        // On  fait  un teste  avant  l'ajout  de chaque  objet   au cas ou  la donnée  est  vide elle 
        //s'affiche  pas dans la legend 
     
                if(datapmufmfiabilite.data.length > 0 )options.series.push(datapmufmfiabilite);
                if(datapmfiabilite.data.length > 0 )  options.series.push(datapmfiabilite);
                if(datapmufmpsfiabilite.data.length > 0 ) options.series.push(datapmufmpsfiabilite);
                if(datapmpcpsfiabilite.data.length > 0 )  options.series.push(datapmpcpsfiabilite);
        
                /* Les Points     x */
                if(datapm_ufm.data.length > 0 ) options.series.push(datapm_ufm);
                if(datapm.data.length > 0 ) options.series.push(datapm); 
                if(datapm_pc_ufm_ps.data.length > 0 ) options.series.push(datapm_pc_ufm_ps);
                if(datapm_pc_ps.data.length > 0 )  options.series.push(datapm_pc_ps);
                /* Legend  couleure  */
                options.series.push(couleur01);
                options.series.push(couleur02);
                options.series.push(couleur03);
                options.series.push(couleur04);
                options.series.push(couleur05);
                options.series.push(couleur06);
                options.series.push(couleur07);
                options.series.push(couleur08);
                options.series.push(couleur09);
                options.series.push(couleur010);

    options.tooltip = {
        //headerFormat: '<b>{series.name} gygy</b><br>',
        // pointFormat: '{point.x} cm, {point.y} kg',
        backgroundColor: '#F5A9BC',
        borderColor: 'black',
        borderRadius: 10,
        borderWidth: 3,
        color:'#FFFFFF',
        formatter: function () {
          var s ='<b> Topic :</b>:' + this.point.Topic +'<br/>';
          s+= '<b> X: </b>' + this.x + '<br/> ';
          s+= '<b> Y : </b>' + this.y +'<br/>';
          s+= ' <b> N° Document : ' + this.point.numDoc + '</b><br/>';
          s+= ' <b> Glucose Yields[min ,max] :</b> [' + this.point.Glucose_min + ',' +        this.point.Glucose_max + ']<br/>';
          s+= ' <b> E-fator[min ,max] :</b> [' + this.point.E_factore_Min + ',' +this.point.E_factore_Max + ']<br/>';
            
     

            return s;
        }

    }
   
    console.log(options);
    var chart = new Highcharts.Chart(options);

    });
    
}