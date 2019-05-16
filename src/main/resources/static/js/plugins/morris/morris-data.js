// Morris.js Charts sample data for SB Admin template

$(function() {
    // Donut Chart
    Morris.Donut({
        element: 'morris-donut-chart',
        data: [{
            label: "Download Sales",
            value: 20
        }, {
            label: "In-Store Sales",
            value: 10
        }, {
            label: "Mail-Order Sales",
            value: 30
        }],
        resize: true
    });
    
});
