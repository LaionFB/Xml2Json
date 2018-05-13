# Xml to Json

This app parses a Xml file, converts it Json format and saves it in a new file.

* It ignores tags with attributes.

Input Example:
```
<?xml version="1.0" encoding="UTF-8"?>
<pokemon>
    <name>Charizard</name>
    <number>6</number>
    <types>
	<type>fire</type>
	<type>flying</type>
    </types>
    <moves>
	<move>
	    <name>Flamethrower</name>
	    <type>fire</type>
	    <category>special</category>
	    <power>90</power>
	</move>
	<move>
	    <name>Dragon Claw</name>
	    <type>dragon</type>
	    <category>physical</category>
	    <power>80</power>
	</move>
	<move>
	    <name>Earthquake</name>
	    <type>ground</type>
	    <category>physical</category>
	    <power>100</power>
	</move>
	<move>
	    <name>Roost</name>
	    <type>flying</type>
	    <category>status</category>
	    <power>-</power>
	</move>
    </moves>
</pokemon>
```
Output:
```
{
	"name": "Charizard",
	"number": "6",
	"types": [
		"fire",
		"flying"
	],
	"moves": [
		{
			"name": "Flamethrower",
			"type": "fire",
			"category": "special",
			"power": "90"
		},
		{
			"name": "Dragon Claw",
			"type": "dragon",
			"category": "physical",
			"power": "80"
		},
		{
			"name": "Earthquake",
			"type": "ground",
			"category": "physical",
			"power": "100"
		},
		{
			"name": "Roost",
			"type": "flying",
			"category": "status",
			"power": "-"
		}
	]
}
```