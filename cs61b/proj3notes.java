routing and locatio ndata provided in berkeley.osm

xml: markup language for encoding data indocument

parsing the xml file: example in mapdbhandler

param:

states that we have: node way  

tags: consists of key and value, describe a feature of a data element, nodes, ways or relations.k and v free fromat. "key=value"
key:a broad class of features
value: specified feature that was generally classified by the key such as "highway=motorway"
way: an ordered list of noeds which has at least one tag or is included within a relation

route search
/raster rceives optionally four values for input: the start point's long and la, and endpoint's long and la.
implement findanderawroute// seems like to use it to build sth

the route should be the shortest path 