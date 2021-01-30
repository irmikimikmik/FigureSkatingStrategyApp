# Strategy Determinator for Figure Skating Choreographies

## by Clover (also known as w1q0i or Irmak Bayir)

#### General Information:

- The reason this project is of interest to me is:

As the Turkish National Figure Skating Champion since 2016, **determining a strategy for my choreographies has played
a huge role in my life**. At this point, I know specific points for 100+ elements and I am aware of the result I will
get depending on what I put in my choreography as well as where I put it. However, gaining this ability took me
22 international competitions in 12 countries, 15 gold medals and 2 records in the history of Turkish female figure
skaters.

I have always had this idea for a project of mine because **not all professional figure skaters should have to go
through this painful process of learning extreme technicalities along with all the physical training** they are doing.
So I would love to make their lives easier, even if that means that I may lose my records.

- This application will:

Detect two types of components of figure skating which are *skating skills* and *technical* components. These
scores are determined by the judges however, it is possible to predict the scores you will get in a competition prior
to competing if the right calculations are done with the right parameters.
With this application, **users will be able to see an approximation of the score they will get with their current
choreography**. Thus, according to the aim of the competitor (whether it is earning a medal, ranking at a certain place or
getting a specific point), the users will be able to determine if they should change or switch certain elements.
With the help of this application, they may decide to go with an easier choreography (where elements have lower points)
but choose to get more bonus points from the judges instead of going with a harder choreography (where elements have
higher points) but risk the bonus points they will get.

- The people who will use this project are:

**Figure skating coaches** and **figure skaters** all around the world, specifically the ones competing in Challenge
Series, Grand Prix competitions or European/World Championships.

#### Details of how this application will work:

The way this application will predict the score is that figure skaters/coaches will select the elements
in the choreography. Each element has its own variation of scores. This variation occurs due to the bonus points that
the judges may add to the *base point* of the element, or the points they may take from that element (also known as
*GOE scores*) depending on the quality of it. So once the elements in the choreography are selected as well
as the GOE scores, the application will come up with the *technical score* of that choreography. The *skating
skills* component however completely depends on your presentation of the choreohraphy and how much the judges have
enjoyed it rather than certain elements. This skill is a skill that comes with experience and confidence on the ice
so the application will need some data for this. There will be slots for the user to enter the skating
skills scores they have received in the past competitions. Once the user enters these scores in a reverse-chronological
way, the application will predict their skating skills score for the next competition by constructing a graph of the
previous data and making an estimation with the line of best fit. Once both of the scores are calculated, they will be
demonstrated on the screen and the user will know whether they should make adjustments or compete with the choreohraphy
they have.

#### *USER STORIES*

- As a user, I want to be able to add an element to my choreohraphy.
- As a user, I want to be able to add my previous points to my skating skills component history.
- As a user, I want to be able to choose which element I am adding.
- As a user, I want to view the elements I have already added to my choreography.
- As a user, I want the option to not add a GOE score as I may not have an idea about what the judges will 
think of the element.