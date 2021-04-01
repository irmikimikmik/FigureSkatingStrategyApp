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
to competing if the right calculations are done with the right parameters. An important factor to consider while making
these calculations is the *elements* in one's choreography. Elements are special movements done by the skaters during
their choreographies to get points and elements have 3 main types: jumps, spins and steps.

With this application, **users will be able to see an approximation of the score they will get with their current
choreography**. Thus, according to the aim of the competitor (whether it is earning a medal, ranking at a certain place
or getting a specific point), the users will be able to determine if they should change or switch certain elements.
So with the help of this strategy determinator, they may decide to go with an easier choreography (where 
elements have lower points) but choose to get more bonus points from the judges instead of going with a harder
choreography (where elements have higher points) but risk the bonus points they will get.

- The people who will use this project are:

**Senior level figure skating coaches** and **senior level figure skaters** all around the world, specifically the ones
competing in Challenge Series, Grand Prix competitions or European/World Championships.

#### Details of how this application will work:

The way this application will predict the score is that figure skaters/coaches will select the elements
in the choreography. Each element has its own variation of scores. This variation occurs due to the bonus points that
the judges may add to the *base point* of the element, or the points they may take from that element (also known as
*GOE scores*) depending on the quality of it. So once the elements in the choreography are selected as well
as the GOE scores, the application will come up with the *technical score* of that choreography. The *skating
skills* component however completely depends on your presentation of the choreography and how much the judges have
enjoyed it rather than certain elements. This skill is one that comes with experience and confidence on the ice,
so the application will just prompt the user to enter a skating skills score they have received (most preferably a
recent one).

It would not be practical for this application to estimate the skating skills score depending on multiple results from
the past rather than the most recent one because these scores are very reliant on who the judges are, what type of
energy the skater radiates that day on the ice and even the difficulty of the competition. Since the skating skills
component is not as concrete as the technical component and it relies on so many and very different factors, adding more
scores from the past simply wouldn't contribute to the precision of the prediction. That is why only a single score of
skating skills would be enough.

Once both of the scores are calculated, they will be demonstrated on the screen and the user will know whether they
should make adjustments or compete with the choreography they have.

#### *USER STORIES*

- As a user, I want to be able to add an element to my choreography.
- As a user, I want to be able to choose which element I am adding.
- As a user, I want to view the elements I have already added to my choreography.
- As a user, I want the option to not add a GOE score as I may not have an idea about what the judges will 
think of the element.
- As a user, I would like to see if the duration of my choreography fits the standards of the Ice Skating Union rule
book.
- As a user, I want to be able to save my choreography to file.
- As a user, I want to be able to be able to load my choreography from file.
- As a user, when I start the application, I want to be given the option to load my choreography from file.

#### Phase 4: Task 2
"Test and design a class in your model package that is robust.  You must have at least one method that throws a checked 
exception.  You must have one test for the case where the exception is expected and another where the exception is not 
expected."

    Class with robust design: model.Choreography
    Method with robust design: basePointFinder(String elementName)
    How is it robust: basePointFinder considers the scenario of the user not entering a valid element in the field and
    thus, throws and exception if it cannot find the element that was written by the user in the CSV file that contains
    all the base points of every single element in figure skating
    When is the exception caught: actionPerformed(ActionEvent e) in ui.PopUpWindow catches IOException that was thrown
    when the input of the user can't be found by the CSV file reader in any lines of the file
    !!! basePointFinder method has its 2 tests in the ChoreographyTest class where the first test written for it expects
    the exception and fails if it is not caught and the second one doesn't expect and exception and fails when an
    exception is caught.
